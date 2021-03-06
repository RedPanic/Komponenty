package circlebean;

import guitools.GuiTools;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Circle implements Serializable {
    private Double ring;
    private boolean isDiameter;

    public Circle() {

    }

    public Circle(Double value, boolean isDiameter) {
        if (isDiameter) {
            this.ring = (value / 2.0);
        } else {
            this.ring = value;
        }
    }

    public Double getRing() {
        return ring;
    }

    public void setRing(Double ring) {
        this.ring = ring;
    }

    public Double calcField(Double value) {
        Double field = Double.valueOf(Math.round(Math.PI * Math.pow(value, 2)));

        return field;
    }

    public Double calcCircum(Double value) {
        Double circum = 2 * Math.PI * value;

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

    public Circle deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Circle deserialized = (Circle) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;
    }

}
