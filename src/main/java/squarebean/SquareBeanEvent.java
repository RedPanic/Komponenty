package squarebean;

import java.util.EventObject;

public class SquareBeanEvent extends EventObject {
    private Double number;
    private String elementName, selectedElement;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    //Kontruktor do obsługi obliczeń
    public SquareBeanEvent(Object source, Double number, String elementName) {
        super(source);
        this.number = number;
        this.elementName = elementName;
    }

    //Kontruktor do obsługi deserializacji
    public SquareBeanEvent(Object source, String elementName, String selectedElement) {
        super(source);
        this.elementName = elementName;
        this.selectedElement = selectedElement;
    }

    //Kontruktor do obsługi deserializacji
    public SquareBeanEvent(Object source, Double number, String elementName, String selectedElement) {
        super(source);
        this.number = number;
        this.elementName = elementName;
        this.selectedElement = selectedElement;
    }

    public String getSelectedElement() {
        return selectedElement;
    }

    public String getElementName() {
        return elementName;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
