package circlebean;

import java.util.EventObject;

public class CirleBeanEvent extends EventObject {
    private Double number;
    private String elementName, selectedQuantity, selectedOperation;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CirleBeanEvent(Object source, Double number, String elementName, String selectedQuantity) {
        super(source);
        this.number = number;
        this.selectedQuantity = selectedQuantity;
        this.elementName = elementName;
    }

    public CirleBeanEvent(Object source, String elementName, String selectedOperation) {
        super(source);
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
    }

    public CirleBeanEvent(Object source, Double number, String elementName, String selectedQuantity, String selectedOperation) {
        super(source);
        this.number = number;
        this.elementName = elementName;
        this.selectedQuantity = selectedQuantity;
        this.selectedOperation = selectedOperation;
    }

    public String getElementName() {
        return elementName;
    }

    public String getSelectedQuantity() {
        return selectedQuantity;
    }

    public String getSelectedOperation() {
        return selectedOperation;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getNumber() {
        return number;
    }
}
