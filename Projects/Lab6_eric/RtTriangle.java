package Lab6_eric;
import java.util.Scanner;

public class RtTriangle extends Shape {
    
    private double base = 0;

    private double height = 0;

    public RtTriangle() {
        super("RtTriangle");
    }

    public RtTriangle(double b, double h) {
        super("RtTriangle");
        this.base = b;
        this.height = h;
}
    
    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }
    
    public double computeArea() {
        return (base * height) / 2;
    }

    public double computePerimeter() {
        return base + height + Math.sqrt(Math.pow(base + height,2));
    }
    
    public void readShapeData() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base of the Right Triangle");
        base = in.nextDouble();
        System.out.println("Enter height of the Right Triangle");
        height = in.nextDouble();
        in.close();
}

    public String toString() {
        return super.toString() + ": base is " + base + "and height is " + height ;
}
}
