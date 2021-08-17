/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.komponenty;


import circlebean.Circle;
import circlebean.CircleBeanEventListener;
import circlebean.CircleBeanPanel;
import circlebean.CircleBeanEvent;
import guitools.GuiTools;
import squarebean.SquareBeanEvent;
import squarebean.SquareBeanEventListener;
import squarebean.SquareBeanPanel;
import squarebean.Square;
import trianglebean.TriangleBeanPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


/**
 * @author r00ser
 * Michał Postek
 */
public class MainWindow extends JFrame {

    private Image icon;
    private List<ImageIcon> tabIcons;
    private JTabbedPane tabs;
    private SquareBeanPanel squareBeanPanel;
    private CircleBeanPanel circleBeanPanel;
    private TriangleBeanPanel triangleBeanPanel;


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
                Square square = new Square();

                if (elementName.equals(squareBeanPanel.getCalcBtn().getText())) {
                    square.setSideLength(event.getNumber());
                    String msg = "Bok twojego kwadratu ma długość " + square.getSideLength() + "\n"
                            + "Jego obwód wynosi " + square.calcCircum(square.getSideLength()) + "\n"
                            + "Jego pole wynosi " + square.calcField(square.getSideLength());
                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    if (event.getSelectedElement().equals(squareBeanPanel.getSerializeRb().getText())) {
                        square.setSideLength(event.getNumber());
                        square.serialize(squareBeanPanel.getFilePathTf().getText());
                    } else {
                        square = square.deserialize(squareBeanPanel.getFilePathTf().getText());
                        squareBeanPanel.getSideLengthTf().setText(String.valueOf(square.getSideLength()));

                    }

                }
            }
        });

        tabs.addTab("Kwadrat", tabIcons.get(1), squareBeanPanel, "Operacje dla kwadratu");

        circleBeanPanel = new CircleBeanPanel();

        circleBeanPanel.setListener(new CircleBeanEventListener() {
            @Override
            public void CirleBeanEventOccured(CircleBeanEvent event) throws IOException {
                String elementName = event.getElementName();


                if (elementName.equals(circleBeanPanel.getCalcBtn().getText())) {
                    if (event.getSelectedQuantity().equals(circleBeanPanel.getDiameterRb().getText())) {
                        Circle circleBean = new Circle(event.getNumber(), true);
                        String msg = "Pole koła o promieniu: " + circleBean.getRing() + "\n"
                                + "wynosi: " + circleBean.calcField(circleBean.getRing())
                                + ".\n" + "Jego obwód wynosi: " + circleBean.calcCircum(circleBean.getRing())
                                + ".\n";

                        GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Circle circleBean = new Circle(event.getNumber(), false);
                        String msg = "Pole koła o promieniu: " + circleBean.getRing() + "\n"
                                + "wynosi: " + circleBean.calcField(circleBean.getRing())
                                + ".\n" + "Jego obwód wynosi: " + circleBean.calcCircum(circleBean.getRing())
                                + ".\n";

                        GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    Circle bean = new Circle();
                    if (event.getSelectedOperation().equals(circleBeanPanel.getSerializeRb().getText())) {
                        bean = (circleBeanPanel.getRingRb().isSelected()) ? new Circle(event.getNumber(), false) : new Circle(event.getNumber(), true);
                        bean.serialize(circleBeanPanel.getFilePathTf().getText());
                    } else {
                        bean = bean.deserialize(circleBeanPanel.getFilePathTf().getText());
                        circleBeanPanel.getLengthValueTf().setText(String.valueOf(bean.getRing()));
                        circleBeanPanel.getRingRb().setSelected(true);
                    }

                }
            }
        });

        tabs.addTab("Koło i okrąg", tabIcons.get(2), circleBeanPanel, "Operacje dostępne dla koła i okręgu");

        triangleBeanPanel = new TriangleBeanPanel();

        tabs.addTab("Trójkąt", tabIcons.get(3), triangleBeanPanel, "Operacje dostępne dla trójkąta");

        this.add(tabs);
        this.setVisible(true);
    }


}
