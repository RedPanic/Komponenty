package circlebean;

import guitools.GuiTools;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class CircleBeanPanel extends JPanel {
    private GridBagConstraints gc;

    private JLabel titleLbl, lengthValueLbl, filePathLbl;
    private JTextField filePathTf, lengthValueTf;
    private JButton submitBtn, calcBtn;
    private JRadioButton diameterRb, ringRb, serializeRb, deserializeRb;

    private CircleBeanEventListener listener;

    public CircleBeanPanel() {
        this.initUI();
    }

    private void initUI() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();

        /*                  FIRST ROW                       */

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 150, 75, 0);
        gc.gridwidth = 3;

        titleLbl = new JLabel("Obliczanie pola i obwodu koła");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);



        /*                      SECOND ROW                          */

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 0, 15, 80);

        lengthValueLbl = new JLabel("Podaj długość");
        this.add(lengthValueLbl, gc);

        gc.gridx = 5;
        gc.gridy = 1;

        filePathLbl = new JLabel("Ścieżka do pliku");
        this.add(filePathLbl, gc);


        /*                      THIRD ROW                          */

        gc.gridx = 0;
        gc.gridy = 2;

        lengthValueTf = new JTextField(15);
        this.add(lengthValueTf, gc);

        gc.gridx = 5;
        gc.gridy = 2;

        filePathTf = new JTextField(15);
        this.add(filePathTf, gc);

        ButtonGroup group1 = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();

        /*                      FOURTH ROW                          */

        gc.gridx = 0;
        gc.gridy = 3;

        diameterRb = new JRadioButton("Średnica (d)");
        this.add(diameterRb, gc);

        gc.gridx = 5;
        gc.gridy = 3;

        deserializeRb = new JRadioButton("Wczytaj dane z pliku");
        this.add(deserializeRb, gc);

        /*                      FIFTH ROW                          */

        gc.gridx = 0;
        gc.gridy = 4;

        ringRb = new JRadioButton("Promień (r)");
        ringRb.setSelected(true);
        this.add(ringRb, gc);

        gc.gridx = 5;
        gc.gridy = 4;

        serializeRb = new JRadioButton("Zapisz dane do pliku");
        serializeRb.setSelected(true);
        this.add(serializeRb, gc);

        /*                      SIXTH ROW                          */


        gc.gridx = 0;
        gc.gridy = 5;

        calcBtn = new JButton("Oblicz");
        this.add(calcBtn, gc);

        gc.gridx = 5;
        gc.gridy = 5;

        submitBtn = new JButton("Wykonaj");
        this.add(submitBtn, gc);

        group1.add(ringRb);
        group1.add(diameterRb);

        group2.add(serializeRb);
        group2.add(deserializeRb);

        this.addListeners();

        this.setVisible(true);

    }

    private void addListeners() {

        calcBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedElement = (diameterRb.isSelected()) ? diameterRb.getText() : ringRb.getText();
                CircleBeanEvent event = null;

                try {
                    Double number = Double.parseDouble(lengthValueTf.getText());
                    event = new CircleBeanEvent(this, number, calcBtn.getText(), selectedElement);
                } catch (NumberFormatException nfe) {
                    GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                if (listener != null) {
                    try {
                        listener.circleBeanEventOccured(event);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (serializeRb.isSelected()) ? serializeRb.getText() : deserializeRb.getText();
                CircleBeanEvent event = null;

                if (selectedAction.equals(serializeRb.getText())) {
                    String selectedQuantity = (ringRb.isSelected()) ? ringRb.getText() : diameterRb.getText();
                    try {
                        Double number = Double.parseDouble(lengthValueTf.getText());
                        event = new CircleBeanEvent(this, number, submitBtn.getText(), selectedQuantity, selectedAction);
                    } catch (NumberFormatException nfe) {
                        GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }


                } else {
                    event = new CircleBeanEvent(this, submitBtn.getText(), selectedAction);

                }

                if (listener != null) {
                    try {
                        listener.circleBeanEventOccured(event);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
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


    public JTextField getFilePathTf() {
        return filePathTf;
    }

    public JTextField getLengthValueTf() {
        return lengthValueTf;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public JButton getCalcBtn() {
        return calcBtn;
    }

    public JRadioButton getDiameterRb() {
        return diameterRb;
    }

    public JRadioButton getRingRb() {
        return ringRb;
    }

    public JRadioButton getSerializeRb() {
        return serializeRb;
    }

    public JRadioButton getDeserializeRb() {
        return deserializeRb;
    }

    public void setListener(CircleBeanEventListener listener) {
        this.listener = listener;
    }
}
