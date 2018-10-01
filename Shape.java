import java.util.ArrayList;

/** creates the abstract class Shape
 *
 */
public abstract class Shape implements Displayable, Comparable<Shape> {
    public abstract double area();
    public abstract double perimeter();
    public abstract String getShape();
    public static int shapeCount;

    public void display(){
        System.out.println("This is a ");
        System.out.print(this.getShape());
        System.out.println(" with an area of ");
        System.out.print(this.area());
        System.out.print(" and a perimeter of ");
        System.out.println(this.perimeter());

    }
}




