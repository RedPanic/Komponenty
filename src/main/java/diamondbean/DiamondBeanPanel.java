package diamondbean;

import guitools.GuiTools;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class DiamondBeanPanel extends JPanel {

    private JLabel titleLbl, diametersLengthLbl, filePathLbl;
    private JTextField diameterELengthTf, diameterFLengthTf, filePathTf;
    private JButton calcBtn, submitBtn;
    private JRadioButton serializeRb, deserializeRb;

    private GridBagConstraints gc;

    private DiamondBeanEventListener listener;

    public DiamondBeanPanel() { this.initUI();
    }

    private void initUI() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();

        /* FIRST ROW */

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 280, 50, 0);
        gc.gridwidth = 3;

        titleLbl = new JLabel("Obliczanie pola i obwodu rombu");
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        GuiTools.setLabelFont(titleLbl, 18);
        this.add(titleLbl, gc);

        /* SECOND ROW */

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 50, 30, 30);

        diametersLengthLbl = new JLabel("Długość przekątnych");
        this.add(diametersLengthLbl, gc);

        gc.gridx = 3;
        gc.gridy = 1;

        filePathLbl = new JLabel("Ścieżka do pliku");
        this.add(filePathLbl, gc);

        /* THIRD ROW */

        gc.gridx = 0;
        gc.gridy = 2;

        diameterELengthTf = new JTextField();
        this.add(diameterELengthTf, gc);

        gc.gridx = 3;
        gc.gridy = 2;

        filePathTf = new JTextField();
        this.add(filePathTf, gc);

        /* FOURTH ROW */

        gc.gridx = 0;
        gc.gridy = 3;

        diameterFLengthTf = new JTextField();
        this.add(diameterFLengthTf, gc);

        gc.gridx = 3;
        gc.gridy = 3;

        deserializeRb = new JRadioButton("Wczytaj dane z pliku");
        deserializeRb.setSelected(true);
        this.add(deserializeRb, gc);

        /* FIFTH ROW */

        gc.gridx = 0;
        gc.gridy = 4;

        calcBtn = new JButton("Oblicz");
        this.add(calcBtn, gc);

        gc.gridx = 3;
        gc.gridy = 4;

        serializeRb = new JRadioButton("Zapisz dane do pliku");
        this.add(serializeRb, gc);

        /* SIXTH ROW */

        gc.gridx = 3;
        gc.gridy = 5;

        submitBtn = new JButton("Wykonaj");
        this.add(submitBtn, gc);

        ButtonGroup group = new ButtonGroup();

        group.add(serializeRb);
        group.add(deserializeRb);

        this.addListeners();
        this.setVisible(true);
    }

    private void addListeners() {

        calcBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DiamondBeanEvent event = null;

                try{
                    Double diameterE = Double.parseDouble(diameterELengthTf.getText());
                    Double diameterF = Double.parseDouble(diameterFLengthTf.getText());
                    event = new DiamondBeanEvent(this, diameterE, diameterF, calcBtn.getText());

                }catch (NumberFormatException nfe) {
                    GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                if (listener != null) {

                    try {
                        listener.diamondBeanEventOccured(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                }
            }
        });

        submitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOperation = (serializeRb.isSelected()) ? serializeRb.getText() : deserializeRb.getText();
                DiamondBeanEvent event = null;

                if (selectedOperation.equals(serializeRb.getText())) {
                    try{
                        Double diameterE = Double.parseDouble(diameterELengthTf.getText());
                        Double diameterF = Double.parseDouble(diameterFLengthTf.getText());
                        event = new DiamondBeanEvent(this, diameterE, diameterF, submitBtn.getText(),serializeRb.getText());
                    }catch(NumberFormatException nfe){
                        GuiTools.MessageBox("Nie wpisano liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    event = new DiamondBeanEvent(this, submitBtn.getText(), deserializeRb.getText());
                }

                if (listener != null) {

                    try {
                        listener.diamondBeanEventOccured(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
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

    public JTextField getDiameterELengthTf() {
        return diameterELengthTf;
    }

    public void setDiameterELengthTf(JTextField diameterELengthTf) {
        this.diameterELengthTf = diameterELengthTf;
    }

    public JTextField getDiameterFLengthTf() {
        return diameterFLengthTf;
    }

    public void setDiameterFLengthTf(JTextField diameterFLengthTf) {
        this.diameterFLengthTf = diameterFLengthTf;
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

    public DiamondBeanEventListener getListener() {
        return listener;
    }

    public void setListener(DiamondBeanEventListener listener) {
        this.listener = listener;
    }
}
