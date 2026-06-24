import java.util.HashMap;
import java.util.Map;

class FineRepository {
    private Map<String, Double> unpaidFines = new HashMap<>();

    public void addFine(String plate, double amount) {
        unpaidFines.put(plate, unpaidFines.getOrDefault(plate, 0.0) + amount);
    }

    public double getUnpaidFine(String plate) {
        return unpaidFines.getOrDefault(plate, 0.0);
    }

    public void clearFine(String plate) {
        unpaidFines.remove(plate);
    }
    
    public Map<String, Double> getAllFines() {
    return unpaidFines;
    }

}
