package parallelogrambean;

import guitools.GuiTools;
import squarebean.Square;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Parallelogram implements Serializable {

    private Double sideALength, sideBLength, height, circum, field;

    public Parallelogram() {
    }

    public Double calcField(double sideALength, double height){
        Double field = sideALength * height;
        return field;
    }

    public Double calcCircum(double sideALength, double sideBLength){
        Double circum = ((2*sideALength)+ (2*sideBLength));
        return circum;
    }

    public Parallelogram(Double sideALength, Double sideBLength, Double height) {
        this.sideALength = sideALength;
        this.sideBLength = sideBLength;
        this.height = height;
    }

    public Double getSideALength() {
        return sideALength;
    }

    public void setSideALength(Double sideALength) {
        this.sideALength = sideALength;
    }

    public Double getSideBLength() {
        return sideBLength;
    }

    public void setSideBLength(Double sideBLength) {
        this.sideBLength = sideBLength;
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

    public Parallelogram deserialize(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        Parallelogram deserialized = (Parallelogram) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;

    }



}
