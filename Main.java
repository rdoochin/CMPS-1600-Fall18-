public class Main {
    public static void main(String args []){


        /*
        myLong first = new myLong();
        first.setLong("111111110000000002929292929292");
        System.out.println(first.getLong());//3
        */


        myLong newLong = new myLong();
        myLong newLong2 = new myLong();

        //System.out.print(newLong.mylong);

       // newLong.setLong("6");
        //newLong2.setLong("20");
        //newLong.getLong();
       // newLong2.getLong();

     //  myLong addition = newLong.addLong(newLong2);

    //   System.out.println(addition.getLong());

      // myLong subtraction = newLong.subtractLong(newLong2);

       // System.out.println(subtraction.getLong());

        //myLong mult = newLong.multiplyLong(newLong2);
        //System.out.println(mult.getLong());


        String answer;
        String in = "(1+2)*(3+1)";
        Calculate input = new Calculate(in);
        answer = input.calculate();
        System.out.println(answer);

    }
}
