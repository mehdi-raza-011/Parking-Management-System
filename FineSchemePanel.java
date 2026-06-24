import javax.swing.*;
import java.awt.*;

public class FineSchemePanel extends JPanel {

    private JRadioButton fixedBtn, progressiveBtn, hourlyBtn;
    private JButton saveBtn;

    public FineSchemePanel() {

        setLayout(new GridLayout(5, 1));

        fixedBtn = new JRadioButton("Fixed Fine (RM50 Flat)");
        progressiveBtn = new JRadioButton("Progressive Fine Scheme");
        hourlyBtn = new JRadioButton("Hourly Fine (RM20/hour)");

        // Default selected
        fixedBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(fixedBtn);
        group.add(progressiveBtn);
        group.add(hourlyBtn);

        saveBtn = new JButton("Apply Fine Scheme");

        saveBtn.addActionListener(e -> applyFineScheme());

        add(new JLabel("Select Fine Calculation Scheme:", JLabel.CENTER));
        add(fixedBtn);
        add(progressiveBtn);
        add(hourlyBtn);
        add(saveBtn);
    }

    private void applyFineScheme() {

        FineScheme scheme = null;
        String selectedScheme = "";

        if (fixedBtn.isSelected()) {
            scheme = new FixedFineScheme();
            selectedScheme = "Fixed";
        }
        else if (progressiveBtn.isSelected()) {
            scheme = new ProgressiveFineScheme();
            selectedScheme = "Progressive";
        }
        else if (hourlyBtn.isSelected()) {
            scheme = new HourlyFineScheme();
            selectedScheme = "Hourly";
        }

        // Apply strategy to system
        ParkingLotSystem.getInstance().setFineScheme(scheme);

        JOptionPane.showMessageDialog(this,
                "Fine Scheme set to: " + selectedScheme +
                "\n(Applies immediately)");
    }
}
