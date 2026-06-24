import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerGUI extends JFrame {

    public CustomerGUI() {

        setTitle("Customer Parking Interface");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Entry", createEntryPanel());
        tabs.add("Exit & Payment", createPaymentPanel());

        add(tabs);
        setVisible(true);
    }

    // ================= ENTRY PANEL =================

    private JPanel createEntryPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField plateField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(
                new String[]{"Motorcycle", "Car", "SUV", "Handicapped"}
        );
        JComboBox<String> spotBox = new JComboBox<>();

        JButton viewSpotsBtn = new JButton("View Available Spots");
        JButton enterBtn = new JButton("Park Vehicle");

        inputPanel.add(new JLabel("Plate Number:"));
        inputPanel.add(plateField);
        inputPanel.add(new JLabel("Vehicle Type:"));
        inputPanel.add(typeBox);
        inputPanel.add(new JLabel(""));
        inputPanel.add(viewSpotsBtn);
        inputPanel.add(new JLabel("Select Spot:"));
        inputPanel.add(spotBox);
        inputPanel.add(new JLabel(""));
        inputPanel.add(enterBtn);

        JTextArea ticketDisplay = new JTextArea();
        ticketDisplay.setEditable(false);

        viewSpotsBtn.addActionListener(e -> {

            spotBox.removeAllItems();
            String selectedType = typeBox.getSelectedItem().toString();

            List<ParkingSpot> spots =
                    ParkingLot.getInstance()
                            .getAvailableSpots(selectedType);

            if (spots.isEmpty()) {
                JOptionPane.showMessageDialog(panel,
                        "No spots available.");
            } else {
                for (ParkingSpot s : spots) {
                    spotBox.addItem(s.getSpotId());
                }
            }
        });

        enterBtn.addActionListener(e -> {

            String plate = plateField.getText().trim().toUpperCase();
            String type = typeBox.getSelectedItem().toString();
            String spotId = (String) spotBox.getSelectedItem();

            if (plate.isEmpty() || spotId == null) {
                JOptionPane.showMessageDialog(panel,
                        "Please enter plate and select spot.");
                return;
            }

            Ticket ticket =
                    ParkingLotSystem.getInstance()
                            .parkVehicle(plate, type, spotId);

            if (ticket == null) {
                JOptionPane.showMessageDialog(panel,
                        "Parking failed.");
                return;
            }

            ticketDisplay.setText(
                    "=== PARKING TICKET ===\n" +
                    "Ticket ID: " + ticket.ticketId + "\n" +
                    "Spot ID: " + ticket.spotId + "\n" +
                    "Plate: " + ticket.plate + "\n" +
                    "Entry Time: " + ticket.entryTime
            );
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(ticketDisplay), BorderLayout.CENTER);

        return panel;
    }

    // ================= PAYMENT PANEL =================

    private JPanel createPaymentPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());

        JTextField plateField = new JTextField(15);
        JButton payBtn = new JButton("Pay & Exit");

        topPanel.add(new JLabel("Plate Number:"));
        topPanel.add(plateField);
        topPanel.add(payBtn);

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);

        payBtn.addActionListener(e -> {

            String plate = plateField.getText().trim().toUpperCase();

            if (plate.isEmpty()) {
                JOptionPane.showMessageDialog(panel,
                        "Enter plate number.");
                return;
            }

            Payment payment = new CashPayment(0);

            Receipt receipt =
                    ParkingLotSystem.getInstance()
                            .processExit(plate, payment);

            if (receipt == null) {
                JOptionPane.showMessageDialog(panel,
                        "Vehicle not found or payment failed.");
                return;
            }

            receiptArea.setText(receipt.toString());
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        return panel;
    }
}
