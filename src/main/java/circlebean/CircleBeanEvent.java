package circlebean;

import java.util.EventObject;

public class CircleBeanEvent extends EventObject {
    private Double number;
    private String elementName, selectedQuantity, selectedOperation;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CircleBeanEvent(Object source, Double number, String elementName, String selectedQuantity) {
        /* CALC BUTTON */
        super(source);
        this.number = number;
        this.selectedQuantity = selectedQuantity;
        this.elementName = elementName;
    }

    public CircleBeanEvent(Object source, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (READ) */
        super(source);
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
    }

    public CircleBeanEvent(Object source, Double number, String elementName, String selectedQuantity, String selectedOperation) {
        /* SUBMIT BUTTON (WRITE) */
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
