package squarebean;

import guitools.GuiTools;

import javax.swing.*;
import java.awt.*;

public class SquareBeanPanel extends JPanel {

    private GridBagConstraints gc;

    private JLabel titleLbl, sideLenghtLbl, filePathLbl;
    private JTextField filePathTf, sideLengthTf;
    private JButton submitBtn, calcBtn;
    private JRadioButton serializeRb, deserializeRb;

    public SquareBeanPanel() {
        this.initUI();

    }

    private void initUI() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.ipady = 120;
        gc.gridwidth = 5;

        titleLbl = new JLabel("Obliczanie pola i obwodu kwadratu");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipady = 15;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy = 1;

        sideLenghtLbl = new JLabel("Długość boku");
        GuiTools.setLabelFont(sideLenghtLbl, 14);
        this.add(sideLenghtLbl,gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 4;
        gc.gridy = 1;

        filePathLbl = new JLabel("Ścieżka do pliku");
        GuiTools.setLabelFont(filePathLbl, 14);
        this.add(filePathLbl, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipadx = 20;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0,0,20,20);

        sideLengthTf = new JTextField(20);
        this.add(sideLengthTf, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipadx = 0;
        gc.gridx = 4;
        gc.gridy = 2;

        filePathTf = new JTextField(20);
        this.add(filePathTf, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipadx = 20;
        gc.ipady = 20;
        gc.gridx = 0;
        gc.gridy = 3;

        calcBtn = new JButton("Oblicz");
        this.add(calcBtn, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipadx = 0;
        gc.gridx = 4;
        gc.gridy = 3;

        submitBtn = new JButton("Wykonaj");
        this.add(submitBtn, gc);

        this.setVisible(true);
    }


}
