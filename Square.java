/**
 * a subclass of Rectangle
 */
public class Square extends Rectangle {
    public Square(double s) {
        super(s, s);
    }

    @Override
    public String getShape(){
        return "Square";
    }
}
