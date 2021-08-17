package trianglebean;

import java.util.EventObject;

public class TriangleBeanEvent extends EventObject {
    private Double sideA, sideB, sideC, height;
    private String elementName, selectedOperation;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TriangleBeanEvent(Object source, Double sideA, Double sideB, Double sideC, Double height, String elementName) {
        /*  CALC BUTTON  */
        super(source);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.height = height;
        this.elementName = elementName;
    }

    public TriangleBeanEvent(Object source, Double sideA, Double sideB, Double sideC, Double height, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (WRITE) */
        super(source);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.height = height;
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
    }

    public TriangleBeanEvent(Object source, String elementName, String selectedOperation) {
        super(source);
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
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
