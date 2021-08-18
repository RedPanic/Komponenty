package trianglebean;

import guitools.GuiTools;

import javax.swing.*;
import java.awt.*;

public class TriangleBeanPanel extends JPanel {
    private GridBagConstraints gc;

    private JLabel titleLbl, sidesLenghtLbl, filePathLbl, descriptionLbl, heightLenghtLbl;
    private JTextField sideALenghtTf, sideBLengthTf, sideCLengthTf, heightLengthTf, filePathTf;
    private JButton calcBtn, submitBtn;
    private JRadioButton serializeRb, deserializeRb;

    private TriangleBeanEventListener listener;

    public TriangleBeanPanel() {
        this.initUI();
    }

    private void initUI() {
        this.setPreferredSize(new Dimension(640,480));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();


        /*                      FIRST ROW                            */

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 280, 30, 0);
        gc.gridwidth = 3;

        titleLbl = new JLabel("Obliczanie pola i obwodu trójkąta");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);



        /*  SECOND ROW */

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 280, 100, 0);

        descriptionLbl = new JLabel("Dotyczy trójkąta różnobocznego");
        descriptionLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(descriptionLbl, gc);


        /*
            TODO: FINNISH GUI AND ADD EVENT HANDLING
        */


        /*  THIRD ROW */

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 50, 30, 70);

        sidesLenghtLbl = new JLabel("Długość boków");
        this.add(sidesLenghtLbl, gc);


        gc.gridx = 3;
        gc.gridy = 2;

        filePathLbl = new JLabel("Ścieżka do pliku");
        this.add(filePathLbl, gc);


        /*  FOURTH ROW */

        gc.gridx = 0;
        gc.gridy = 3;

        sideALenghtTf = new JTextField();
        this.add(sideALenghtTf, gc);

        gc.gridx = 3;
        gc.gridy = 3;

        filePathTf = new JTextField(21);
        this.add(filePathTf, gc);

        /*  FIFTH ROW */

        gc.gridx = 0;
        gc.gridy = 4;

        sideBLengthTf = new JTextField(21);
        this.add(sideBLengthTf, gc);

        gc.gridx = 3;
        gc.gridy = 4;

        deserializeRb = new JRadioButton("Wczytaj dane z pliku");
        this.add(deserializeRb, gc);

        /*  SIXTH ROW */

        gc.gridx = 0;
        gc.gridy = 5;

        sideCLengthTf = new JTextField(21);
        this.add(sideCLengthTf, gc);

        gc.gridx = 3;
        gc.gridy = 5;

        serializeRb = new JRadioButton("Zapisz dane do pliku");
        this.add(serializeRb, gc);

        /*  SEVENTH ROW */

        gc.gridx = 0;
        gc.gridy = 6;

        heightLenghtLbl = new JLabel("Wysokość");
        this.add(heightLenghtLbl, gc);

        gc.gridx = 3;
        gc.gridy = 6;

        submitBtn = new JButton("Wykonaj");
        this.add(submitBtn, gc);


        /*  EIGHTH ROW */

        gc.gridx = 0;
        gc.gridy = 7;

        heightLengthTf = new JTextField(21);
        this.add(heightLengthTf, gc);

        /*  NINETH ROW */

        gc.gridx = 0;
        gc.gridy = 8;

        calcBtn = new JButton("Oblicz");
        this.add(calcBtn, gc);

        ButtonGroup group = new ButtonGroup();

        group.add(serializeRb);
        group.add(deserializeRb);

        this.addListeners();
        this.setVisible(true);
    }

    private void addListeners() {
    }
}
