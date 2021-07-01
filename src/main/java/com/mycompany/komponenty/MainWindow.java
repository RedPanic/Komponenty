/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.komponenty;


import guitools.GuiTools;
import squarebean.SquareBeanEvent;
import squarebean.SquareBeanEventListener;
import squarebean.SquareBeanPanel;
import squarebean.SquareBean;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * @author new
 */
public class MainWindow extends JFrame {

    private Image icon;
    private List<ImageIcon> tabIcons;
    private JTabbedPane tabs;
    private SquareBeanPanel squareBeanPanel;


    public MainWindow() {
        this.setTitle("Figures Calc v0.1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        icon = Toolkit.getDefaultToolkit().getImage("src/main/java/static/calculator.png");
        this.setIconImage(icon);
        this.setLocationRelativeTo(null);
        this.setSize(800, 600);

        tabs = new JTabbedPane();
        tabIcons = GuiTools.addIcons("src/main/java/static/");

        squareBeanPanel = new SquareBeanPanel();
        squareBeanPanel.setListener(new SquareBeanEventListener() {

            @Override
            public void SquareBeanEventOccured(SquareBeanEvent event) {
                String elementName = event.getElementName();
                SquareBean square = new SquareBean();

                if (elementName.equals(squareBeanPanel.getCalcBtn().getText())) {
                    square.setSideLength(event.getNumber());
                    String msg = "Bok twojego kwadratu ma długość " + square.getSideLength() + "\n"
                            + "Jego obwód wynosi " + square.calcCircum(square.getSideLength()) + "\n"
                            + "Jego pole wynosi " + square.calcField(square.getSideLength());
                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //TODO wywołanie serializacji i deserializacji
                }
            }
        });

        tabs.addTab("Kwadrat", tabIcons.get(1), squareBeanPanel, "Operacje dla kwadratu");

        this.add(tabs);
        this.setVisible(true);
    }


}
