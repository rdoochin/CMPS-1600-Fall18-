import org.jetbrains.annotations.NotNull;

/** a subclass of Shape
 *
 */
public class Circle extends Shape {

    double radius;

    public Circle(double r){
        radius = r;
        shapeCount++;
    }

    //@Override
    public double area(){
        double area = (Math.PI) * radius * radius;
        return area;
    }

    @Override
    public double perimeter() {
        double perim = 2 * (Math.PI) * radius;
        return perim;

    }

    @Override
    public String getShape() {
        return "Circle";
    }

    @Override
    public int compareTo(@NotNull Shape o) {
        return 0;
    }
}
