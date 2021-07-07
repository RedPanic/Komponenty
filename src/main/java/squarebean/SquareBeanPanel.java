package squarebean;

import guitools.GuiTools;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SquareBeanPanel extends JPanel {

    private GridBagConstraints gc;

    private JLabel titleLbl, sideLengthLbl, filePathLbl;
    private JTextField filePathTf, sideLengthTf;
    private JButton submitBtn, calcBtn;
    private JRadioButton serializeRb, deserializeRb;

    private SquareBeanEventListener listener;

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
        gc.insets = new Insets(0, 0, 100, 0);
        gc.gridwidth = 6;

        titleLbl = new JLabel("Obliczanie pola i obwodu kwadratu");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipady = 15;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.gridx = 0;
        gc.gridy = 1;

        sideLengthLbl = new JLabel("Długość boku");
        GuiTools.setLabelFont(sideLengthLbl, 14);
        this.add(sideLengthLbl, gc);

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
        gc.insets = new Insets(0, 0, 20, 20);

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

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 5;
        gc.gridy = 2;
        gc.ipadx = 40;

        ButtonGroup group = new ButtonGroup();

        serializeRb = new JRadioButton("Zapisz dane wejściowe do pliku");
        serializeRb.setSelected(true);
        this.add(serializeRb, gc);

        gc.gridy = 3;

        deserializeRb = new JRadioButton("Wczytaj dane wejściowe z pliku");
        this.add(deserializeRb, gc);

        group.add(serializeRb);
        group.add(deserializeRb);


        calcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double number = Double.parseDouble(sideLengthTf.getText());
                    SquareBeanEvent event = new SquareBeanEvent(this, number, calcBtn.getText());

                    if (listener != null) {
                        listener.SquareBeanEventOccured(event);
                    }
                } catch (NumberFormatException | IOException nfe) {
                    GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedElement = (serializeRb.isSelected()) ? serializeRb.getText() : deserializeRb.getText();
                SquareBeanEvent event = null;
                if (selectedElement.equals(serializeRb.getText())) {
                    try {
                        Double number = Double.parseDouble(sideLengthTf.getText());
                        event = new SquareBeanEvent(this, number, submitBtn.getText(), selectedElement);
                    } catch (NumberFormatException nfe) {
                        GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    event = new SquareBeanEvent(this, submitBtn.getText(), selectedElement);
                }


                if (listener != null) {
                    try {
                        listener.SquareBeanEventOccured(event);
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


        this.setVisible(true);
    }

    public JTextField getSideLengthTf() {
        return sideLengthTf;
    }

    public JTextField getFilePathTf() {
        return filePathTf;
    }

    public JButton getCalcBtn() {
        return calcBtn;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public JRadioButton getSerializeRb() {
        return serializeRb;
    }

    public JRadioButton getDeserializeRb() {
        return deserializeRb;
    }

    public void setListener(SquareBeanEventListener listener) {
        this.listener = listener;
    }
}
