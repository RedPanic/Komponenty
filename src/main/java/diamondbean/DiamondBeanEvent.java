package diamondbean;

import java.util.EventObject;

public class DiamondBeanEvent extends EventObject {

    private Double diameterE, diameterF;
    private String elementName, selectedOperation;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    public DiamondBeanEvent(Object source, Double diameterE, Double diameterF, String elementName) {
        /* CALC BUTTON */
        super(source);
        this.diameterE = diameterE;
        this.diameterF = diameterF;
        this.elementName = elementName;
    }

    public DiamondBeanEvent(Object source, Double diameterE, Double diameterF, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (WRITE) */
        super(source);
        this.diameterE = diameterE;
        this.diameterF = diameterF;
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
    }

    public DiamondBeanEvent(Object source, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (READ) */
        super(source);
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
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

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getSelectedOperation() {
        return selectedOperation;
    }

    public void setSelectedOperation(String selectedOperation) {
        this.selectedOperation = selectedOperation;
    }
}
