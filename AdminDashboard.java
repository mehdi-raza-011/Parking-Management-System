import javax.swing.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Panel - Parking Lot System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Parking Lot View", new ParkingLotPanel());
        tabbedPane.addTab("Reports", new ReportsPanel());
        tabbedPane.addTab("Fine Scheme", new FineSchemePanel());

        add(tabbedPane);
        setVisible(true);
    }
}
