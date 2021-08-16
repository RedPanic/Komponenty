package circlebean;

import guitools.GuiTools;
import squarebean.SquareBean;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class CircleBean implements Serializable {
    private Double ring;
    private boolean isDiameter;

    public CircleBean() {

    }

    public CircleBean(Double value, boolean isDiameter){
        if(isDiameter){
            this.ring = (value/2.0);
        }
        else{
            this.ring = value;
        }
    }

    public Double getRing() {
        return ring;
    }

    public void setRing(Double ring) {
        this.ring = ring;
    }

    public Double calcField(Double value, boolean isDiameter ){
        Double field = null;
        if(isDiameter){
            field = Double.valueOf(Math.round(Math.PI * Math.pow((value/2),2)));
        }
        else{
            field = Double.valueOf(Math.round(Math.PI * Math.pow(value,2)));
        }

        return field;
    }

    public Double calcCircum(Double value, boolean isDiameter ){
        Double circum = null;

        if(isDiameter){
            circum = 2 * Math.PI * (value / 2);
        }
        else{
            circum = 2 * Math.PI * value;
        }

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

    public CircleBean deserialize(String filename) throws  IOException{
        FileInputStream fis = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(fis);
        CircleBean deserialized = (CircleBean) decoder.readObject();
        decoder.close();
        fis.close();
        return deserialized;
    }

}
