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
import trianglebean.Triangle;
import trianglebean.TriangleBeanEvent;
import trianglebean.TriangleBeanEventListener;
import trianglebean.TriangleBeanPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


/**
 * @author r00ser
 * Michał Postek
 */
public class MainWindow extends JFrame implements ActionListener {

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

        /* MENU */

        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Program");
        JMenuItem about = new JMenuItem("O programie..");
        JMenuItem exit = new JMenuItem("Zakończ");

        about.addActionListener(this);
        exit.addActionListener(this);

        mainMenu.add(about);
        mainMenu.add(new JSeparator());
        mainMenu.add(exit);

        menuBar.add(mainMenu);

        this.setJMenuBar(menuBar);


        /* OTHER COMPONENTS */

        tabs = new JTabbedPane();
        tabIcons = GuiTools.addIcons("src/main/java/static/");

        squareBeanPanel = new SquareBeanPanel();
        squareBeanPanel.setListener(new SquareBeanEventListener() {

            @Override
            public void squareBeanEventOccured(SquareBeanEvent event) throws IOException {
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
            public void circleBeanEventOccured(CircleBeanEvent event) throws IOException {
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

        triangleBeanPanel.setListener(new TriangleBeanEventListener() {
            @Override
            public void triangleBeanEventOccured(TriangleBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                Triangle triangle = new Triangle();

                if(elementName.equals(triangleBeanPanel.getCalcBtn().getText())){
                    triangle.setA(event.getSideA());
                    triangle.setB(event.getSideB());
                    triangle.setC(event.getSideC());
                    triangle.setHeight(event.getHeight());

                    String msg = "Pole trójkąta o podstawie: " + triangle.getA() + "\n"+
                            "wynosi: " + triangle.calcField(triangle.getA(), triangle.getHeight()) + ".\n"+
                            "Jego obwód wynosi: " + triangle.calcCircum(triangle.getA(), triangle.getB(), triangle.getC())+ ".";

                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);

                }else{
                    if(event.getSelectedOperation().equals(triangleBeanPanel.getSerializeRb().getText())){
                        triangle.setA(event.getSideA());
                        triangle.setB(event.getSideB());
                        triangle.setC(event.getSideC());
                        triangle.setHeight(event.getHeight());
                        triangle.serialize(triangleBeanPanel.getFilePathTf().getText());

                    }else{
                        triangle = triangle.deserialize(triangleBeanPanel.getFilePathTf().getText());
                        triangleBeanPanel.getSideALengthTf().setText(String.valueOf(triangle.getA()));
                        triangleBeanPanel.getSideBLengthTf().setText(String.valueOf(triangle.getB()));
                        triangleBeanPanel.getSideCLengthTf().setText(String.valueOf(triangle.getC()));
                        triangleBeanPanel.getHeightLengthTf().setText(String.valueOf(triangle.getHeight()));
                    }

                }
            }
        });

        tabs.addTab("Trójkąt", tabIcons.get(0), triangleBeanPanel, "Operacje dostępne dla trójkąta");

        this.add(tabs);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            switch(actionCommand) {
                case "O programie..":{
                    GuiTools.MessageBox("Program wykonany na potrzeby przedmiotu \"Zastosowanie programowania komponenetowego\".\n Wykonał: Michał Postek U-14847", "O programie", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Zakończ":
                {
                    this.dispose();
                    break;
                }
                default:
                {
                    GuiTools.MessageBox("Błąd wewnętrzny aplikacji", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }

    }
}
