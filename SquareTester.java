import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;

public class SquareTesterTest extends TestCase {

    /*
    initializing variables
     */
    Square s1;
    Square s2;
    Square s3;
    Square s4;

    protected void setUp(){
        System.out.print("Setting up the test.");
    }

    protected void setUp(double x, double y, double z, double a){
        System.out.print("Setting up the test.");
        s1 = new Square(x);
        s2 = new Square(y);
        s3 = new Square(z);
        s4 = new Square(a);
    }

    public void testSide(){
        setUp(12,3453,0, 100);
        System.out.print("Testing the getSide() method.");
        double side1 = s1.getSide();
        double side2 = s2.getSide();
        double side3 = s3.getSide();
        double side4 = s4.getSide();
        Assert.assertTrue(side1 == 5);
        Assert.assertTrue(side2 ==3453);
        Assert.assertTrue(side3 == 0);
        Assert.assertTrue(side4 == 100);
        tearDown();
    }

    public void testString(){
        System.out.print("Testing the toString() method.");
        String string1 = s1.toString();
        String string2 = s2.toString();
        String string3 = s3.toString();
        String string4 = s4.toString();
        Assert.assertThat(string1, CoreMatchers.is("This is a square with side length: 5.0"));
        Assert.assertThat(string2, CoreMatchers.is("This is a square with side length: 3453.0"));
        Assert.assertThat(string3, CoreMatchers.is("This is a square with side length: 0.0"));
        tearDown();

    }

    public void testEquals(){
        System.out.print("Testing the equals() method");
        setUp(2,2,12, 12);

        Assert.assertTrue(s1.equals(s2));
        Assert.assertTrue(s2.equals(s3));
        Assert.assertTrue(s3.equals(s4));
        tearDown();

    }


    protected void tearDown(){
        System.out.print("Running tearDown()");
        s1 = null;
        s2 = null;
        s3 = null;
        s4 = null;
        assertNull(s1);
        assertNull(s2);
        assertNull(s3);
        assertNull(s4);
    }

    @Test
    public void TestTheSquare(){
        testSide();
        testString();
        testEquals();

    }
}
