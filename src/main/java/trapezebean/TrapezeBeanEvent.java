package trapezebean;

import java.util.EventObject;

public class TrapezeBeanEvent extends EventObject {

    private Double sideA, sideB, sideC, sideD, height;
    private String elementName, selectedOperation;

    public TrapezeBeanEvent(Object source, Double sideA, Double sideB, Double sideC, Double sideD, Double height, String elementName) {
        /* CALC BUTTON */
        super(source);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
        this.height = height;
        this.elementName = elementName;
    }

    public TrapezeBeanEvent(Object source, Double sideA, Double sideB, Double sideC, Double sideD, Double height, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (WRITE) */
        super(source);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
        this.height = height;
        this.elementName = elementName;
        this.selectedOperation = selectedOperation;
    }

    public TrapezeBeanEvent(Object source, String elementName, String selectedOperation) {
        /* SUBMIT BUTTON (READ) */
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
