package Lab6_eric;
import java.util.Scanner;

public class Circle extends Shape {
    
    private double radius = 0;

    public Circle() {
        super("Circle");
    }

    public Circle(double r) {
        super("Circle");
        this.radius = r;
}
    
    public double getRadius() {
        return radius;
    }

    
    public double computeArea() {
        return Math.PI * Math.pow(radius,2);
    }

    public double computePerimeter() {
        return Math.PI * (2 * radius);
    }
    
    public void readShapeData() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the radius of the circle");
        radius = in.nextDouble();
        in.close();
}

    public String toString() {
        return super.toString() + ": radius is " + radius ;
}
}
