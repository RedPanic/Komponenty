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
import diamondbean.Diamond;
import diamondbean.DiamondBeanEvent;
import diamondbean.DiamondBeanEventListener;
import diamondbean.DiamondBeanPanel;
import guitools.GuiTools;
import parallelogrambean.Parallelogram;
import parallelogrambean.ParallelogramBeanEvent;
import parallelogrambean.ParallelogramBeanEventListener;
import parallelogrambean.ParallelogramBeanPanel;
import squarebean.SquareBeanEvent;
import squarebean.SquareBeanEventListener;
import squarebean.SquareBeanPanel;
import squarebean.Square;
import trapezebean.Trapeze;
import trapezebean.TrapezeBeanEvent;
import trapezebean.TrapezeBeanEventListener;
import trapezebean.TrapezeBeanPanel;
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
    private ParallelogramBeanPanel parallelogramBeanPanel;
    private DiamondBeanPanel diamondBeanPanel;
    private TrapezeBeanPanel trapezeBeanPanel;


    public MainWindow() {
        this.setTitle("Figures Calc v0.1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        icon = Toolkit.getDefaultToolkit().getImage("src/main/java/static/main/calculator.png");
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
        tabIcons = GuiTools.addIcons("src/main/java/static/tabs");

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

        tabs.addTab("Kwadrat", tabIcons.get(0), squareBeanPanel, "Operacje dla kwadratu");

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

        tabs.addTab("Koło i okrąg", tabIcons.get(1), circleBeanPanel, "Operacje dostępne dla koła i okręgu");

        triangleBeanPanel = new TriangleBeanPanel();

        triangleBeanPanel.setListener(new TriangleBeanEventListener() {
            @Override
            public void triangleBeanEventOccured(TriangleBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                Triangle triangle = new Triangle();

                if (elementName.equals(triangleBeanPanel.getCalcBtn().getText())) {
                    triangle.setA(event.getSideA());
                    triangle.setB(event.getSideB());
                    triangle.setC(event.getSideC());
                    triangle.setHeight(event.getHeight());

                    String msg = "Pole trójkąta o podstawie: " + triangle.getA() + "\n" +
                            "wynosi: " + triangle.calcField(triangle.getA(), triangle.getHeight()) + ".\n" +
                            "Jego obwód wynosi: " + triangle.calcCircum(triangle.getA(), triangle.getB(), triangle.getC()) + ".";

                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    if (event.getSelectedOperation().equals(triangleBeanPanel.getSerializeRb().getText())) {
                        triangle.setA(event.getSideA());
                        triangle.setB(event.getSideB());
                        triangle.setC(event.getSideC());
                        triangle.setHeight(event.getHeight());
                        triangle.serialize(triangleBeanPanel.getFilePathTf().getText());

                    } else {
                        triangle = triangle.deserialize(triangleBeanPanel.getFilePathTf().getText());
                        triangleBeanPanel.getSideALengthTf().setText(String.valueOf(triangle.getA()));
                        triangleBeanPanel.getSideBLengthTf().setText(String.valueOf(triangle.getB()));
                        triangleBeanPanel.getSideCLengthTf().setText(String.valueOf(triangle.getC()));
                        triangleBeanPanel.getHeightLengthTf().setText(String.valueOf(triangle.getHeight()));
                    }

                }
            }
        });

        tabs.addTab("Trójkąt", tabIcons.get(2), triangleBeanPanel, "Operacje dostępne dla trójkąta");

        parallelogramBeanPanel = new ParallelogramBeanPanel();

        parallelogramBeanPanel.setListener(new ParallelogramBeanEventListener() {
            @Override
            public void parallelogramBeanEventOccured(ParallelogramBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                Parallelogram parallelogram = new Parallelogram();

                if (elementName.equals(parallelogramBeanPanel.getCalcBtn().getText())) {
                    parallelogram.setSideALength(event.getSideA());
                    parallelogram.setSideBLength(event.getSideB());
                    parallelogram.setHeight(event.getHeight());

                    String msg = "Pole równoległoboku o podstawie: " + parallelogram.getSideALength() + "\n" +
                            "i wysokości: " + parallelogram.getHeight() + "\n" +
                            "wynosi: " + parallelogram.calcField(parallelogram.getSideALength(), parallelogram.getHeight()) + "\n" +
                            "Jego obwód wynosi: " + parallelogram.calcCircum(parallelogram.getSideALength(), parallelogram.getSideBLength());

                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    if (event.getSelectedOperation().equals(triangleBeanPanel.getSerializeRb().getText())) {
                        parallelogram.setSideALength(event.getSideA());
                        parallelogram.setSideBLength(event.getSideB());
                        parallelogram.setHeight(event.getHeight());
                        parallelogram.serialize(parallelogramBeanPanel.getFilePathTf().getText());

                    } else {
                        parallelogram = parallelogram.deserialize(parallelogramBeanPanel.getFilePathTf().getText());
                        parallelogramBeanPanel.getSideALengthTf().setText(String.valueOf(parallelogram.getSideALength()));
                        parallelogramBeanPanel.getSideBLengthTf().setText(String.valueOf(parallelogram.getSideBLength()));
                        parallelogramBeanPanel.getHeightLengthTf().setText(String.valueOf(parallelogram.getHeight()));
                    }
                }
            }
        });

        tabs.addTab("Równoległobok", tabIcons.get(3), parallelogramBeanPanel, "Operacje dostępne dla równoległoboku");

        diamondBeanPanel = new DiamondBeanPanel();

        diamondBeanPanel.setListener(new DiamondBeanEventListener() {
            @Override
            public void diamondBeanEventOccured(DiamondBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                Diamond diamond = new Diamond();

                if(elementName.equals(diamondBeanPanel.getCalcBtn().getText())) {
                    diamond.setDiameterE(event.getDiameterE());
                    diamond.setDiameterF(event.getDiameterF());

                    String msg = "Pole rombu o przekątnych: " + diamond.getDiameterE() +" oraz " +
                            diamond.getDiameterF() +"\n"+
                            "wynosi: " + diamond.calcField(diamond.getDiameterE(), diamond.getDiameterF()) + "\n" +
                            "Jego obwód wynosi: "+ diamond.calcCircum(diamond.getDiameterE(), diamond.getDiameterF());

                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);

                }else{
                    if(event.getSelectedOperation().equals(diamondBeanPanel.getSerializeRb().getText())){
                        diamond.setDiameterE(event.getDiameterE());
                        diamond.setDiameterF(event.getDiameterF());
                        diamond.serialize(diamondBeanPanel.getFilePathTf().getText());
                    }else{
                        diamond = diamond.deserialize(diamondBeanPanel.getFilePathTf().getText());
                        diamondBeanPanel.getDiameterELengthTf().setText(String.valueOf(diamond.getDiameterE()));
                        diamondBeanPanel.getDiameterFLengthTf().setText(String.valueOf(diamond.getDiameterF()));

                    }
                }

            }
        });

        tabs.addTab("Romb", tabIcons.get(4), diamondBeanPanel, "Operacje dostępne dla rombu");

        trapezeBeanPanel = new TrapezeBeanPanel();

        trapezeBeanPanel.setListener(new TrapezeBeanEventListener() {
            @Override
            public void trapezeBeanEventOccured(TrapezeBeanEvent event) throws IOException {
                String elementName = event.getElementName();
                Trapeze trapeze = new Trapeze();

                if(elementName.equals(trapezeBeanPanel.getCalcBtn().getText())){
                    trapeze.setSideA(event.getSideA());
                    trapeze.setSideB(event.getSideB());
                    trapeze.setSideC(event.getSideC());
                    trapeze.setSideD(event.getSideD());
                    trapeze.setHeight(event.getHeight());
                    String sides = "("+ trapeze.getSideA() +", " + trapeze.getSideB() +", " + trapeze.getSideC() +", " + trapeze.getSideD() + ")";

                    String msg = "Pole trapezu o bokach: " + sides + "\n" +
                            "i wysokości: " + trapeze.getHeight() +"\n" +
                            "wynosi: " + trapeze.calcField(trapeze.getSideA(), trapeze.getSideB(), trapeze.getHeight()) +"\n"+
                            "Jego obwód wynosi: "+ trapeze.calcCircum(trapeze.getSideA(), trapeze.getSideB(), trapeze.getSideC(), trapeze.getSideD());

                    GuiTools.MessageBox(msg, "Wyniki obliczeń", JOptionPane.INFORMATION_MESSAGE);

                }else{

                    if(event.getSelectedOperation().equals(trapezeBeanPanel.getSerializeRb().getText())){
                        trapeze.setSideA(event.getSideA());
                        trapeze.setSideB(event.getSideB());
                        trapeze.setSideC(event.getSideC());
                        trapeze.setSideD(event.getSideD());
                        trapeze.setHeight(event.getHeight());
                        trapeze.serialize(trapezeBeanPanel.getFilePathTf().getText());
                    }else{
                        trapeze = trapeze.deserialize(trapezeBeanPanel.getFilePathTf().getText());
                        trapezeBeanPanel.getSideALengthTf().setText(String.valueOf(trapeze.getSideA()));
                        trapezeBeanPanel.getSideBLengthTf().setText(String.valueOf(trapeze.getSideB()));
                        trapezeBeanPanel.getSideCLengthTf().setText(String.valueOf(trapeze.getSideC()));
                        trapezeBeanPanel.getSideDLengthTf().setText(String.valueOf(trapeze.getSideD()));
                        trapezeBeanPanel.getHeightLengthTf().setText(String.valueOf(trapeze.getHeight()));

                    }


                }
            }
        });

        tabs.addTab("Trapez", tabIcons.get(5), trapezeBeanPanel, "Operacje dostępne dla trapezu");
        
        this.add(tabs);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "O programie..": {
                GuiTools.MessageBox("Program wykonany na potrzeby przedmiotu \"Zastosowanie programowania komponenetowego\".\n Wykonał: Michał Postek U-14847", "O programie", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Zakończ": {
                System.exit(0);
                break;
            }
            default: {
                GuiTools.MessageBox("Błąd wewnętrzny aplikacji", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
