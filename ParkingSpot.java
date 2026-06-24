public class ParkingSpot {

    private String spotId;
    private int floor;
    private String type; 
    // "Compact", "Regular", "Handicapped", "Reserved"

    private boolean occupied;
    private Vehicle currentVehicle;
    private double hourlyRate;

    public ParkingSpot(String spotId, int floor, String type, double rate) {
        this.spotId = spotId;
        this.floor = floor;
        this.type = type;
        this.hourlyRate = rate;
        this.occupied = false;
    }

    public String getSpotId() {
        return spotId;
    }

    public int getFloor() {
        return floor;
    }

    public String getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void parkVehicle(Vehicle v) {
        this.currentVehicle = v;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.occupied = false;
    }
}
