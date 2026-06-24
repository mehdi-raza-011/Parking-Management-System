public class Fine {
    private String plateNumber;
    private double amount;

    public Fine(String plateNumber, double amount) {
        this.plateNumber = plateNumber;
        this.amount = amount;
    }

    public String getPlateNumber() { return plateNumber; }
    public double getAmount() { return amount; }
}
