import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class myLong {
    private int[] mylong;
    private String inputString;
    public int[] longArray = new int[100];

    public int leg = 0;


    public myLong(){
        mylong = new int[100];
    }
    //gets the long integer from the keyboard

    public void setLong() {
        System.out.println("Enter an integer:");
        Scanner scnr = new Scanner(System.in);
        inputString = scnr.nextLine();
        //System.out.println(inputString);
        leg = inputString.length();
        //int [] longArray = new int[inputString.length()];

        for (int i = 0 ; i< inputString.length() ;i++){
            char c = inputString.charAt(i);
            int val = c - 48;
            //System.out.println(leg);
            this.longArray[i] = val;

            //System.out.print(longArray[i]);
        }
        mylong = longArray;
        System.out.println();
    }


    //displays the long value on the monitor

    public void getLong() {
        for (int i = 0 ; i < inputString.length() ;i++) {
            System.out.print(this.longArray[i]);
        }
        System.out.println();
    }



    //a method to add long integers

    public void addLong(myLong arg) {
        //for (int i = 0 ; i <inputString.length() ;i++){
        //  System.out.print(this.longArray[i]);
        // }

        int retArrayInt[] = new int[100];
        int array1int[] = new int[100];
        int array2int[] = new int[100];
        int x = 0;
        //int end = 99 - this.longArray.length;

        // if (this.longArray.length >= arg.longArray.length) {

        for (int i = this.longArray.length - 1; i > -1; i--) {

                array1int[x] = this.longArray[i];         //creates an array which can store integer values
                array2int[x] = arg.longArray[i];
                x +=1;

                if ((array1int[i] + array2int[i]) > 9) {
                    if (i == 0) {
                        array1int[i] += 1;
                    } else {
                        array1int[i - 1] += 1;
                    }
                }
                retArrayInt[i] = array1int[i] + array2int[i];
        }

       // }
      /**  else{
            for (int i = this.longArray.length -1; i > -1 ; i--) {      //was (int i = 99; i > -1; i--)
                array1int[x] = this.longArray[i];         //creates an array which can store integer values
                array2int[x] = arg.longArray[i];
                x += 1;

                if ((array1int[i] + array2int[i]) > 9) {
                    array1int[i - 1] += 1;
                }
                retArrayInt[i] = array1int[i] + array2int[i];
            }*/

        for (int y = 0 ; y < 100 ;y++){            //(int y = retArrayInt.length-1 ; y> -1 ;y--)
            //System.out.print(retArrayInt[y]);
           System.out.print(array1int[y]);
        }
    }


    //a method to subtract one long integer from another

   public void subtractLong (myLong arg) {
       myLong[] array1Ref = new myLong[100];
       myLong[] array2Ref = new myLong[100];

       int array1int[] = new int[100];
       int array2int[] = new int[100];
       int[] subArray = new int[100];

       for (int i = 99; i > -1; i--) {
           array1int[i] = Integer.parseInt(array1[i]);
           array2int[i] = Integer.parseInt(array2[i]);

           if ((array1int[i] - array2int[i]) < 0) {         //carrying a digit when 0 is what you are subtracting from
               array1int[i] += 1;
               if (array1int[i - 1] == 0) {
                   array1int[i - 2] -= 1;
               } else {
                   array1int[i - 1] -= 1;
               }

           }
           subArray[i] = array1int[i] - array2int[i];
           if (subArray[i] < 0) {
               subArray = subtractLong(array2Ref, array1Ref);
               subArray[0] *= -1;
           }

       }
   }
}