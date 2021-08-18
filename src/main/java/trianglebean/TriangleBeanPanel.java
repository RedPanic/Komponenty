package trianglebean;

import guitools.GuiTools;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TriangleBeanPanel extends JPanel {
    private GridBagConstraints gc;

    private JLabel titleLbl, sidesLengthLbl, filePathLbl, descriptionLbl, heightLengthLbl;
    private JTextField sideALengthTf, sideBLengthTf, sideCLengthTf, heightLengthTf, filePathTf;
    private JButton calcBtn, submitBtn;
    private JRadioButton serializeRb, deserializeRb;

    private TriangleBeanEventListener listener;

    public TriangleBeanPanel() {
        this.initUI();
    }

    private void initUI() {
        this.setPreferredSize(new Dimension(640, 480));
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

        sidesLengthLbl = new JLabel("Długość boków");
        this.add(sidesLengthLbl, gc);


        gc.gridx = 3;
        gc.gridy = 2;

        filePathLbl = new JLabel("Ścieżka do pliku");
        this.add(filePathLbl, gc);


        /*  FOURTH ROW */

        gc.gridx = 0;
        gc.gridy = 3;

        sideALengthTf = new JTextField();
        this.add(sideALengthTf, gc);

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

        heightLengthLbl = new JLabel("Wysokość");
        this.add(heightLengthLbl, gc);

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

        calcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TriangleBeanEvent event = null;
                try {
                    Double sideA = Double.parseDouble(sideALengthTf.getText());
                    Double sideB = Double.parseDouble(sideBLengthTf.getText());
                    Double sideC = Double.parseDouble(sideCLengthTf.getText());
                    Double height = Double.parseDouble(heightLengthTf.getText());
                    event = new TriangleBeanEvent(this, sideA, sideB, sideC, height, calcBtn.getText());

                } catch (NumberFormatException nfe) {
                    GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                if (listener != null) {

                    try {
                        listener.TriangleBeanEventOccured(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                }

            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOperation = (serializeRb.isSelected()) ? serializeRb.getText() : deserializeRb.getText();
                TriangleBeanEvent event = null;

                if (selectedOperation.equals(serializeRb.getText())) {
                    //TODO: ADD SERIALIZATION EVENT
                }else{
                    event = new TriangleBeanEvent(this, submitBtn.getText(), selectedOperation);
                }
            }
        });

        filePathTf.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XML Files (*.xml)", "xml");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showSaveDialog(new JDialog());
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    filePathTf.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }

            }
        });
    }


    public JTextField getSideALengthTf() {
        return sideALengthTf;
    }

    public void setSideALengthTf(JTextField sideALengthTf) {
        this.sideALengthTf = sideALengthTf;
    }

    public JTextField getSideBLengthTf() {
        return sideBLengthTf;
    }

    public void setSideBLengthTf(JTextField sideBLengthTf) {
        this.sideBLengthTf = sideBLengthTf;
    }

    public JTextField getSideCLengthTf() {
        return sideCLengthTf;
    }

    public void setSideCLengthTf(JTextField sideCLengthTf) {
        this.sideCLengthTf = sideCLengthTf;
    }

    public JTextField getHeightLengthTf() {
        return heightLengthTf;
    }

    public void setHeightLengthTf(JTextField heightLengthTf) {
        this.heightLengthTf = heightLengthTf;
    }

    public JTextField getFilePathTf() {
        return filePathTf;
    }

    public void setFilePathTf(JTextField filePathTf) {
        this.filePathTf = filePathTf;
    }

    public JButton getCalcBtn() {
        return calcBtn;
    }

    public void setCalcBtn(JButton calcBtn) {
        this.calcBtn = calcBtn;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(JButton submitBtn) {
        this.submitBtn = submitBtn;
    }

    public JRadioButton getSerializeRb() {
        return serializeRb;
    }

    public void setSerializeRb(JRadioButton serializeRb) {
        this.serializeRb = serializeRb;
    }

    public JRadioButton getDeserializeRb() {
        return deserializeRb;
    }

    public void setDeserializeRb(JRadioButton deserializeRb) {
        this.deserializeRb = deserializeRb;
    }
}
