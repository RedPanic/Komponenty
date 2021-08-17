package trianglebean;

public class Triangle {
    private Double a, b, c, height, field, circum;

    public Triangle(){}

    public Triangle(Double a, Double b, Double c, Double height) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.height = height;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getField() {
        return field;
    }

    public void setField(Double field) {
        this.field = field;
    }

    public Double getCircum() {
        return circum;
    }

    public void setCircum(Double circum) {
        this.circum = circum;
    }

    public Double calcField(Double a, Double height){
        Double field = Double.valueOf(Math.round((a * height)/2));

        return field;
    }

    public Double calcCircum(Double a, Double b, Double c){
        Double circum = a + b + c;
        return circum;
    }
}
