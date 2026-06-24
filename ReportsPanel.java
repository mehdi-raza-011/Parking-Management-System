import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class ReportsPanel extends JPanel {

    private JTextArea reportArea;

    public ReportsPanel() {
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton occupancyBtn = new JButton("View Occupancy Report");
        JButton revenueBtn = new JButton("View Revenue Report");
        JButton vehiclesBtn = new JButton("Vehicles Currently Parked");
        JButton finesBtn = new JButton("Outstanding Fines");

        occupancyBtn.addActionListener(e -> showOccupancy());
        revenueBtn.addActionListener(e -> showRevenue());
        vehiclesBtn.addActionListener(e -> showVehicles());
        finesBtn.addActionListener(e -> showFines());

        buttonPanel.add(occupancyBtn);
        buttonPanel.add(revenueBtn);
        buttonPanel.add(vehiclesBtn);
        buttonPanel.add(finesBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);
    }

    private void showOccupancy() {
    ParkingLot lot = ParkingLot.getInstance();
    double rate = lot.getOccupancyRate();

    reportArea.setText("Occupancy Report\n\nTotal Occupancy: " 
            + String.format("%.2f", rate) + "%");
    }


    private void showRevenue() {
    ParkingLot lot = ParkingLot.getInstance();

    reportArea.setText("Revenue Report\n\nTotal Revenue Collected: RM " 
            + lot.getTotalRevenue());
    }


    private void showVehicles() {
    ParkingLot lot = ParkingLot.getInstance();
    StringBuilder sb = new StringBuilder("Currently Parked Vehicles\n\n");

    for (Vehicle v : lot.getParkedVehicles()) {
        sb.append("Plate: ").append(v.getPlateNumber())
          .append(" | Type: ").append(v.getType()).append("\n");
    }

    reportArea.setText(sb.toString());
    }


    private void showFines() {

    ParkingLotSystem system = ParkingLotSystem.getInstance();
    FineRepository repo = system.getFineRepository();

    StringBuilder sb = new StringBuilder("Outstanding Fines\n\n");

    Map<String, Double> fines = repo.getAllFines();

    if (fines.isEmpty()) {
        sb.append("No outstanding fines.");
    } else {
        for (String plate : fines.keySet()) {
            sb.append("Plate: ")
              .append(plate)
              .append(" | Fine: RM ")
              .append(fines.get(plate))
              .append("\n");
        }
    }

    reportArea.setText(sb.toString());
}



}
