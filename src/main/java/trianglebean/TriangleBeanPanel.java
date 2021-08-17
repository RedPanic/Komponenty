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
        this.setPreferredSize(new Dimension(800,600));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();


        /*                      FIRST ROW                            */

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 100, 50, 100);
        gc.gridwidth = 3;

        titleLbl = new JLabel("Obliczanie pola i obwodu trójkąta");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);



        /*  SECOND ROW */

        gc.gridx = 0;
        gc.gridy = 1;

        descriptionLbl = new JLabel("Dotyczy trójkąta różnobocznego");
        descriptionLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(descriptionLbl, gc);

        /*  THIRD ROW */
        /*  FOURTH ROW */
        /*  FIFTH ROW */
        /*  SIXTH ROW */
        /*  SEVENTH ROW */
        /*  EIGHTH ROW */
        /*  NINETH ROW */

        ButtonGroup group = new ButtonGroup();

        group.add(serializeRb);
        group.add(deserializeRb);

        this.addListeners();
        this.setVisible(true);
    }

    private void addListeners() {
    }
}