package Lab6_eric;

public abstract class Shape {
    
    private String shapeName = ""; 
 
    public Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() { 
        return shapeName; } 
    public String toString() { 
        return "Shape is a " + shapeName; }
    
    public abstract double computeArea();
    public abstract double computePerimeter();
    public abstract void readShapeData();
}

