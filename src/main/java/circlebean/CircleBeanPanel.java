package circlebean;

import guitools.GuiTools;

import javax.swing.*;
import java.awt.*;

public class CircleBeanPanel extends JPanel {
    private GridBagConstraints gc;

    private JLabel titleLbl, lengthValueLbl, filePathLbl;
    private JTextField filePathTf, lengthValueTf;
    private JButton submitBtn, calcBtn;
    private JRadioButton diameterRb, ringRb, serializeRb, deserializeRb;

    public CircleBeanPanel() {
        this.initUI();
    }

    private void initUI(){
        this.setPreferredSize(new Dimension(640, 480));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 100, 0);
        gc.gridwidth = 6;

        titleLbl = new JLabel("Obliczanie pola i obwodu koła");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);

        /* TODO:
        + Add rest of controls (RadioButtons, TextFields, Labels)
        + Create Events and Listeners
        * */

        this.setVisible(true);

    }
}
