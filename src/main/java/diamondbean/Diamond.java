package diamondbean;

import guitools.GuiTools;
import parallelogrambean.Parallelogram;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Diamond implements Serializable {
    private Double diameterE, diameterF, circum, field;

    public Diamond() {
    }

    public Diamond(Double diameterE, Double diameterF) {
        this.diameterE = diameterE;
        this.diameterF = diameterF;
    }

    public Double calcField(double diameterE, double diameterF) {
        Double field = (diameterE * diameterF) / 2;
        return field;
    }

    public Double calcCircum(double diameterE, double diameterF) {
        Double sideLength = Math.sqrt((Math.pow(diameterE, 2) + Math.pow(diameterF, 2)));
        return sideLength * 4;
    }

    public Double getDiameterE() {
        return diameterE;
    }

    public void setDiameterE(Double diameterE) {
        this.diameterE = diameterE;
    }

    public Double getDiameterF() {
        return diameterF;
    }

    public void setDiameterF(Double diameterF) {
        this.diameterF = diameterF;
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

    public Diamond deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Diamond deserialized = (Diamond) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;

    }

}
