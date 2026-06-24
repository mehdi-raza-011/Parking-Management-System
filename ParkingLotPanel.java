import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParkingLotPanel extends JPanel {

    private JTable spotTable;
    private DefaultTableModel model;

    public ParkingLotPanel() {
    setLayout(new BorderLayout());

    String[] columns = {"Spot ID", "Floor", "Type", "Status", "Vehicle Plate"};
    model = new DefaultTableModel(columns, 0);
    spotTable = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(spotTable);

    JButton refreshBtn = new JButton("Refresh Spots");
    refreshBtn.addActionListener(e -> loadSpotData());

    add(new JLabel("Parking Spot Overview", JLabel.CENTER), BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(refreshBtn, BorderLayout.SOUTH);

    loadSpotData(); // ✅ AUTO LOAD ON OPEN
    }


    private void loadSpotData() {
    model.setRowCount(0);

    ParkingLot lot = ParkingLot.getInstance();

    for (ParkingSpot spot : lot.getAllSpots()) {
        String vehiclePlate = spot.isOccupied() ? 
                spot.getCurrentVehicle().getPlateNumber() : "-";

        model.addRow(new Object[]{
                spot.getSpotId(),
                spot.getFloor(),
                spot.getType(),
                spot.isOccupied() ? "Occupied" : "Available",
                vehiclePlate
        });
    }
    }

}
