import java.util.ArrayList;
import java.util.Collections;

public class ShapeTester {

    /**
     * uses the Display interface to print all of the
     * elements in an arrayList.
     */
    public static void displayArray(ArrayList<Shape> arList) {
        for (int x = 0; x < arList.size() ; x++){
            System.out.println(arList.get(x));
        }

    }


    public static void main(String Args[]){

        Triangle tri1 = new Triangle(5,7);
        System.out.println(tri1.getShape());
        System.out.println(tri1.area());

        Circle circ1 = new Circle(4);
        Rectangle rec1 = new Rectangle(6,7);

        /**
         * creates an arrayList with a triangle, circle,
         * and rectangle.
         */
        ArrayList<Shape> AL1 = new ArrayList<>(3);
        AL1.add(tri1);
        AL1.add(circ1);
        AL1.add(rec1);

        for (int x = 0; x < 3 ; x++){
            System.out.println(AL1.get(x).getShape());
        }

        /**
         * prints the position of all of the objects
         * that are a part of the circle class.
         */
        for (int x = 0; x < 3 ; x++){
            if (AL1.get(x).getClass() == Circle.class){
                System.out.print(AL1.get(x).getShape());
                System.out.println(" position: " + x);
            }
        }
        System.out.println(Shape.shapeCount);

        displayArray(AL1);

        Collections.sort(AL1);
        displayArray(AL1);

    }
}
