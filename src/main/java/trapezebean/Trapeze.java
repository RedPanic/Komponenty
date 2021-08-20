package trapezebean;

import diamondbean.Diamond;
import guitools.GuiTools;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Trapeze implements Serializable {
    private Double sideA, sideB, sideC, sideD, height, circum, field;

    public Trapeze() {
    }

    public Trapeze(Double sideA, Double sideB, Double sideC, Double sideD, Double height) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
        this.height = height;
    }

    public Double calcField(double sideA, double sideB, double height){
        Double field = ((sideA + sideB) * height)/2;
        return field;
    }

    public Double calcCircum(double sideA, double sideB, double sideC, double sideD){
        Double circum = sideA + sideB + sideC + sideD;
        return circum;
    }

    public Double getSideA() {
        return sideA;
    }

    public void setSideA(Double sideA) {
        this.sideA = sideA;
    }

    public Double getSideB() {
        return sideB;
    }

    public void setSideB(Double sideB) {
        this.sideB = sideB;
    }

    public Double getSideC() {
        return sideC;
    }

    public void setSideC(Double sideC) {
        this.sideC = sideC;
    }

    public Double getSideD() {
        return sideD;
    }

    public void setSideD(Double sideD) {
        this.sideD = sideD;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getCircum() {
        return circum;
    }

    public void setCircum(Double circum) {
        this.circum = circum;
    }

    public Double getField() {
        return field;
    }

    public void setField(Double field) {
        this.field = field;
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

    public Trapeze deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Trapeze deserialized = (Trapeze) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;

    }
}
