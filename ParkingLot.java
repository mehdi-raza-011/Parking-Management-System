import java.util.*;

public class ParkingLot {

    private static ParkingLot instance;

    private List<ParkingSpot> spots;
    private double totalRevenue;

    private ParkingLot() {
        spots = new ArrayList<>();
        seedData();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    private void seedData() {

        spots.add(new ParkingSpot("F1-R1-S1", 1, "Compact", 2.0));
        spots.add(new ParkingSpot("F1-R1-S2", 1, "Regular", 5.0));
        spots.add(new ParkingSpot("F1-R1-S3", 1, "Regular", 5.0));
        spots.add(new ParkingSpot("F1-R1-S4", 1, "Handicapped", 2.0));
        spots.add(new ParkingSpot("F1-R1-S5", 1, "Reserved", 10.0));

        spots.add(new ParkingSpot("F2-R1-S1", 2, "Compact", 2.0));
        spots.add(new ParkingSpot("F2-R1-S2", 2, "Regular", 5.0));
        spots.add(new ParkingSpot("F2-R1-S3", 2, "Handicapped", 2.0));
        spots.add(new ParkingSpot("F2-R1-S4", 2, "Reserved", 10.0));
    }

    public List<ParkingSpot> getAllSpots() {
        return spots;
    }

    public ParkingSpot findSpotById(String spotId) {
        for (ParkingSpot s : spots) {
            if (s.getSpotId().equals(spotId)) return s;
        }
        return null;
    }

    public List<ParkingSpot> getAvailableSpots(String vehicleType) {

        List<ParkingSpot> available = new ArrayList<>();

        for (ParkingSpot s : spots) {
            if (!s.isOccupied() &&
                isSpotSuitable(s.getType(), vehicleType)) {
                available.add(s);
            }
        }
        return available;
    }

    private boolean isSpotSuitable(String spotType, String vehicleType) {

        if (vehicleType.equalsIgnoreCase("Motorcycle"))
            return spotType.equalsIgnoreCase("Compact");

        if (vehicleType.equalsIgnoreCase("Car"))
            return spotType.equalsIgnoreCase("Compact") ||
                   spotType.equalsIgnoreCase("Regular");

        if (vehicleType.equalsIgnoreCase("SUV"))
            return spotType.equalsIgnoreCase("Regular");

        if (vehicleType.equalsIgnoreCase("Handicapped"))
            return true;

        return false;
    }

    public List<Vehicle> getParkedVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();

        for (ParkingSpot s : spots) {
            if (s.isOccupied() && s.getCurrentVehicle() != null) {
                vehicles.add(s.getCurrentVehicle());
            }
        }
        return vehicles;
    }

    public double getOccupancyRate() {

        long occupied = spots.stream()
                             .filter(ParkingSpot::isOccupied)
                             .count();

        if (spots.size() == 0) return 0;

        return (occupied * 100.0) / spots.size();
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void addRevenue(double amount) {
        totalRevenue += amount;
    }
}
