import org.jetbrains.annotations.NotNull;

/**
 * a subclass of Shape
 */
public class Rectangle extends Shape {

    double length;
    double width;

    public Rectangle (double l, double w){
        length = l;
        width = w;
        shapeCount++;
    }
    @Override
    public double area() {
        double area = length * width;
        return area;
    }

    @Override
    public double perimeter() {
        double perim = (length * 2) + (width * 2);
        return perim;
    }

    @Override
    public String getShape() {
        return "Rectangle";
    }

    @Override
    public int compareTo(@NotNull Shape o) {
        return 0;
    }
}
