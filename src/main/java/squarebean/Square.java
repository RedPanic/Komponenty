/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squarebean;

import guitools.GuiTools;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.io.Serializable;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author new
 */
public class Square implements Serializable {

    private double sideLength;

    public Square() {
    }

    public Square(double a) {
        this.sideLength = a;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double calcField(double sideLength) {
        return sideLength * sideLength;
    }

    public double calcCircum(double sideLength) {
        return sideLength * 4;
    }

    public void serialize(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            @Override
            public void exceptionThrown(Exception e) {

            }

        });
        encoder.writeObject(this);
        encoder.close();
        fos.close();
        GuiTools.MessageBox("Zapisano pomyślnie", "Zapis do pliku", JOptionPane.INFORMATION_MESSAGE);
    }

    public Square deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Square deserialized = (Square) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;

    }

}
