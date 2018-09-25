public class Square extends Rectangle {

    // go back to step 2 of 'square and object'


   // this is the constructor

    public Square(double userLength) {
        super(userLength, userLength);
    }

    @Override
    public String toString(){
        //System.out.println("This is a square with a side length of " + length);
        String s = "This is a square with a side length of " + length;
        return s;
    }

    @Override
    public boolean equals(Object obj){

        boolean boo = false;

        if (obj instanceof Square){
            Square s = (Square) obj;
            if (s.getArea() == ((Square) obj).getArea()){
                boo = true;
            }
        }
        return boo;
    }
}
