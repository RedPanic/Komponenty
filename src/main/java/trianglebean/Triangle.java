package trianglebean;

import circlebean.Circle;
import guitools.GuiTools;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Triangle {
    private Double a, b, c, height, field, circum;

    public Triangle(){}

    public Triangle(Double a, Double b, Double c, Double height) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.height = height;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getField() {
        return field;
    }

    public void setField(Double field) {
        this.field = field;
    }

    public Double getCircum() {
        return circum;
    }

    public void setCircum(Double circum) {
        this.circum = circum;
    }

    public Double calcField(Double a, Double height){
        Double field = Double.valueOf(Math.round((a * height)/2));

        return field;
    }

    public Double calcCircum(Double a, Double b, Double c){
        Double circum = a + b + c;
        return circum;
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

    public Triangle deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Triangle deserialized = (Triangle) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;
    }


}
