/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.komponenty;


import squarebean.SquareBeanPanel;

import javax.swing.*;
import java.awt.*;


/**
 * @author new
 */
public class MainWindow extends JFrame {

    private Image icon;
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
        squareBeanPanel = new SquareBeanPanel();
        tabs.add("Kwadrat", squareBeanPanel);

        this.add(tabs);

        this.setVisible(true);
    }

}
