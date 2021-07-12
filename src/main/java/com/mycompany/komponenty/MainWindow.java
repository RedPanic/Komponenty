/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.komponenty;


import circlebean.CircleBeanPanel;
import guitools.GuiTools;
import squarebean.SquareBeanEvent;
import squarebean.SquareBeanEventListener;
import squarebean.SquareBeanPanel;
import squarebean.SquareBean;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


/**
 * @author new
 */
public class MainWindow extends JFrame {

    private Image icon;
    private List<ImageIcon> tabIcons;
    private JTabbedPane tabs;
    private SquareBeanPanel squareBeanPanel;
    private CircleBeanPanel circleBeanPanel;


    public MainWindow() {
        this.setTitle("Figures Calc v0.1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        icon = Toolkit.getDefaultToolkit().getImage("src/main/java/static/calculator.png");
        this.setIconImage(icon);
        this.setSize(800, 600);

        tabs = new JTabbedPane();
        tabIcons = GuiTools.addIcons("src/main/java/static/");

        squareBeanPanel = new SquareBeanPanel();
        squareBeanPanel.setListener(new SquareBeanEventListener() {

            @Override
            public void SquareBeanEventOccured(SquareBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                SquareBean square = new SquareBean();

                if (elementName.equals(squareBeanPanel.getCalcBtn().getText())) {
                    square.setSideLength(event.getNumber());
                    String msg = "Bok twojego kwadratu ma długość " + square.getSideLength() + "\n"
                            + "Jego obwód wynosi " + square.calcCircum(square.getSideLength()) + "\n"
                            + "Jego pole wynosi " + square.calcField(square.getSideLength());
                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    if(event.getSelectedElement().equals(squareBeanPanel.getSerializeRb().getText())){
                        square.setSideLength(event.getNumber());
                        square.serialize(squareBeanPanel.getFilePathTf().getText());
                    }
                    else{
                        //TODO wywołanie deserializacji
                        square = square.deserialize(squareBeanPanel.getFilePathTf().getText());
                        squareBeanPanel.getSideLengthTf().setText(String.valueOf(square.getSideLength()));

                    }

                }
            }
        });

        for (var icon:tabIcons) {
            System.out.println(icon.getDescription());

        }

        tabs.addTab("Kwadrat", tabIcons.get(2), squareBeanPanel, "Operacje dla kwadratu");

        circleBeanPanel = new CircleBeanPanel();

        tabs.addTab("Koło i okrąg", tabIcons.get(0), circleBeanPanel, "Operacje dostępne dla koła i okręgu");

        this.add(tabs);
        this.setVisible(true);
    }


}
