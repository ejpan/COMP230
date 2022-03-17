package Lab6_eric;
import java.util.Scanner;
public class ComputeAreaAndPerimeter {
    public static void main(String args[]) {
        Shape myShape;
        double perimeter;
        double area;
        myShape = getShape();
        myShape.readShapeData();
        perimeter = myShape.computePerimeter(); 
        area = myShape.computeArea();
        displayResult(myShape, area, perimeter);
        System.exit(0);
    }
    
    public static Shape getShape() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter C for circle");
        System.out.println("Enter R for Rectangle");
        System.out.println("Enter T for Right Triangle");
        String figType = in.next();
        in.close();
        if (figType.equalsIgnoreCase("c")) {
          return new Circle();
        }
        else if (figType.equalsIgnoreCase("r")) {
             return new Rectangle();
        }
        else if (figType.equalsIgnoreCase("t")) {
             return new RtTriangle();
        } else {
             return null;
        }
     }

     private static void displayResult(Shape myShape, double area, double perim) {
        System.out.println(myShape);
        System.out.printf("The area is %.2f%nThe perimeter is %.2f%n",
                          area, perim);
    }
}
