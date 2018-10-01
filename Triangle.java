import org.jetbrains.annotations.NotNull;

/**
 * a subclass of Shape
 */
public class Triangle extends Shape {

    double base;
    double height;

    public Triangle(double b, double h){
        base = b;
        height = h;
        shapeCount ++;
    }

    @Override
    public double area() {
        double area = (base * height)/2;
        return area;
    }

    @Override
    public double perimeter() {
        double parim = base * 3;
        return parim;
    }

    @Override
    public String getShape() {
        return "Triangle";
    }

    @Override
    public int compareTo(@NotNull Shape o) {
        return 0;
    }
}
