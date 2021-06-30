/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.komponenty;


import guitools.GuiTools;
import squarebean.SquareBeanPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        tabs.addTab("Kwadrat", tabIcons.get(1), squareBeanPanel, "Operacje dla kwadratu");

        this.add(tabs);
        this.setVisible(true);
    }


}
