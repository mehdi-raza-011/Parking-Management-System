public class Vehicle {

    private String plateNumber;
    private String type; 
    // "Motorcycle", "Car", "SUV", "Handicapped"

    public Vehicle(String plateNumber, String type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getType() {
        return type;
    }
}
