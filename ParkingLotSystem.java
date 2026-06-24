import java.util.HashMap;
import java.util.Map;
 


public class ParkingLotSystem {

    private static ParkingLotSystem instance;

    private ParkingLot lot;
    private FineRepository fineRepo;
    private FeeCalculator feeCalculator;

    // ✅ Strategy Pattern
    private FineScheme fineScheme;

    private Map<String, Ticket> activeTickets;

    private ParkingLotSystem() {
        lot = ParkingLot.getInstance();
        fineRepo = new FineRepository();
        feeCalculator = new FeeCalculator();

        // Default fine scheme
        fineScheme = new FixedFineScheme();

        activeTickets = new HashMap<>();
    }

    public static ParkingLotSystem getInstance() {
        if (instance == null) {
            instance = new ParkingLotSystem();
        }
        return instance;
    }

    // ✅ Allow Admin to change strategy
    public void setFineScheme(FineScheme scheme) {
        this.fineScheme = scheme;
    }

    // ================= ENTRY PROCESS =================

    public Ticket parkVehicle(String plate, String type, String spotId) {

    plate = plate.trim().toUpperCase();

    ParkingSpot spot = lot.findSpotById(spotId);
    if (spot == null || spot.isOccupied()) return null;

    Vehicle vehicle = new Vehicle(plate, type);
    spot.parkVehicle(vehicle);

    Ticket ticket = new Ticket(plate, spotId);
    activeTickets.put(plate, ticket);

    return ticket;
    }


    // ================= EXIT PROCESS =================

    public Receipt processExit(String plate, Payment payment) {

    plate = plate.trim().toUpperCase();

    if (!activeTickets.containsKey(plate)) return null;

    Ticket ticket = activeTickets.get(plate);
    ParkingSpot spot = lot.findSpotById(ticket.spotId);

    long hours = feeCalculator.calculateHours(ticket.entryTime);
    double rate = spot.getHourlyRate();

    // Handicapped vehicle special rate
    Vehicle vehicle = spot.getCurrentVehicle();

    if (vehicle.getType().equalsIgnoreCase("Handicapped")) {
    rate = 2.0;   // Special discounted rate
    }

    double parkingFee = feeCalculator.calculateParkingFee(hours, rate);


    boolean reservedViolation = 
        spot.getType().equalsIgnoreCase("Reserved") &&
        !ticket.plate.startsWith("VIP");  // Simple rule

    double currentFine = fineScheme.calculateFine(hours, reservedViolation);

    double previousFine = fineRepo.getUnpaidFine(plate);
    double total = parkingFee + currentFine + previousFine;

    payment.amount = total;

    if (!payment.process()) {
    // Store unpaid fine
    if (currentFine > 0) {
        fineRepo.addFine(plate, currentFine);
    }
    return null;
    }

    // Payment successful → clear previous fines
    fineRepo.clearFine(plate);

    lot.addRevenue(total);
    spot.removeVehicle();
    activeTickets.remove(plate);


    return new Receipt(
            ticket.ticketId,
            parkingFee,
            currentFine,
            previousFine,
            total
    );
    }


    public FineRepository getFineRepository() {
        return fineRepo;
    }
}
