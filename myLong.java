import java.util.ArrayList;
import java.util.*;

public class myLong {
    private ArrayList<Integer> mylong;
    ArrayList<Integer> longArray;
    private ArrayList<Integer> bigAdd;      //declaring arrays for the addLong()
    private ArrayList<Integer> lilAdd;


    private String getReturn = "";
    String negInput = "";

    public ArrayList invertArray(){
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = (this.longArray.size()-1); i > -1; i--){
            newList.add(this.longArray.get(i));
        }
        return newList;
    }


    public myLong(){
    }

    /*
    Gets an integer from the keyboard
     */
    public void setLong(String inputString) {

        ArrayList<Integer> temp = new ArrayList<Integer>();

        if(inputString.contains("-")){
            inputString = inputString.substring(1, inputString.length());
            negInput = "neg";
        }


        for (int i = 0 ; i< inputString.length(); i++){

            if(inputString.charAt(0) == '-'){

                temp.add(Integer.parseInt(inputString.substring(i+1, i+2)));
                temp.set(0, temp.get(0)*-1);
            }
            else {
                temp.add(Integer.parseInt(inputString.substring(i, i + 1)));
            }

        }
        longArray = temp;
    }

    /*
    Displays the array on the monitor
     */
    public String getLong() {
        for (int i = 0 ; i < this.longArray.size() ;i++) {
           getReturn = getReturn.concat(this.longArray.get(i).toString());
        }
        if(negInput == "neg"){
            getReturn = "-"+getReturn;
        }
        return getReturn;
    }

    public String toString(){
        return this.getLong();
    }

    public myLong addLong(myLong myLongObj) {

        ArrayList<Integer> answerAdd = new ArrayList<Integer>();

        /*
        Accounting for negative numbers.
         */
        if(this.longArray.toString().contains("-") && myLongObj.longArray.toString().contains("-")){
            String thisSt = this.longArray.toString();
            String myLongObjSt = myLongObj.longArray.toString();
            thisSt = thisSt.replace("-","");
            myLongObjSt = myLongObjSt.replace("-","");

            myLong newThis = new myLong();
            myLong newObj = new myLong();
            newThis.setLong(thisSt);
            newObj.setLong(myLongObjSt);

            return newThis.addLong(newObj);
        }

        if(myLongObj.longArray.size() <= this.longArray.size()) {
            bigAdd = this.longArray;
            lilAdd = myLongObj.longArray;
        }
        else{
            bigAdd = myLongObj.longArray;
            lilAdd = this.longArray;
        }

        int zeros = bigAdd.size() - lilAdd.size();
        for(int i =0; i < zeros; i++){
            lilAdd.add(0, 0);
        }

        int answer = 0;
        for (int i = (bigAdd.size()-1); i > -1; i--) {

                answer = (Integer.valueOf(bigAdd.get(i)) + Integer.valueOf(lilAdd.get(i)));

                if (answer >= 10 && i != 0) {
                    answer -= 10;
                    int newI = i - 1;
                    bigAdd.set(newI, Integer.valueOf(bigAdd.get(newI)) + 1);
                }


                answerAdd.add(0, answer);
        }

      myLong returnAdd = new myLong();
        String newSet = "";
        for(int i=0; i < answerAdd.size(); i++){
            newSet = newSet.concat(answerAdd.get(i).toString());
        }
      returnAdd.setLong(newSet);
      return returnAdd;
    }


    public myLong subtractLong(myLong subOjb){

        ArrayList<Integer> answerSub = new ArrayList<Integer>();
        ArrayList<Integer> invertAnswer = new ArrayList<Integer>();
        ArrayList<Integer> bigSub = new ArrayList<Integer>();
        ArrayList<Integer> lilSub = new ArrayList<Integer>();
        ArrayList<Integer> invertBig = new ArrayList<Integer>();
        ArrayList<Integer> invertLil = new ArrayList<Integer>();
        int counter = 0;
        String negCount = "";

        /*
        Check which array is longer.
        Put the longer array on the 'top'.
        If you have to flip, add negative symbol at end of method.
         */
        if(Integer.valueOf(this.getLong()) > Integer.valueOf(subOjb.getLong())){

            bigSub = this.longArray;
            lilSub = subOjb.longArray;
        }

        if(Integer.valueOf(subOjb.getLong()) > Integer.valueOf(this.getLong())){
            bigSub = subOjb.longArray;
            lilSub = this.longArray;
        }

        if(this.longArray.size() == subOjb.longArray.size()){

            if(Integer.valueOf(this.getLong()) > Integer.valueOf(subOjb.getLong())){
                bigSub = this.longArray;
                lilSub = subOjb.longArray;

            }
            else {
                bigSub = subOjb.longArray;
                lilSub = this.longArray;
                negCount = "neg";
            }
        }
        if(this.longArray.size() < subOjb.longArray.size()){
            bigSub = subOjb.longArray;
            lilSub = this.longArray;
            negCount = "neg";
        }

        /*
        Adds zeros at index 0 of the lilArray so that the
        two arrays are the same length.
         */
        int zeros = bigSub.size() - lilSub.size();
        for(int i =0; i < zeros; i++){
            lilSub.add(0, 0);
        }

        /*
        Inverts the two arrays.
         */
        for (int i = (bigSub.size()-1); i > -1; i--){
            invertBig.add(bigSub.get(i));
            invertLil.add(lilSub.get(i));
        }

        for(int i = 0; i < invertBig.size(); i++){

            while (i != invertBig.size()-1 && invertBig.get(i +1) == '0'){
                counter += 1;
                i += 1;
            }

            /*
            Determining if you need to borrow from the column over.
            */
            if(Integer.valueOf(invertBig.get(i)) < Integer.valueOf(invertLil.get(i))){
                invertBig.set(i + 1, Integer.valueOf(invertBig.get(i+1)) - 1);
                invertBig.set(i, Integer.valueOf(invertBig.get(i)) + 10);
            }


            for(int c = counter; c > i; c--){       // or c < 0 ??
                invertBig.set(i + c, 9);
            }

            /*
            Subtraction at each index of the array.
             */
            invertAnswer.add(Integer.valueOf(invertBig.get(i)) - Integer.valueOf(invertLil.get(i)));

            /*
            Inverting the answer array back to normal.
             */

        }

        for (int j = (invertAnswer.size()-1); j > -1; j--){
            answerSub.add(invertAnswer.get(j));
        }

        myLong answerString = new myLong();
        String strang = "";
        for(int r = 0; r < answerSub.size(); r++){
            strang = strang.concat(answerSub.get(r).toString());
        }


        if(negCount == "neg"){
            strang = "-" + strang;
        }

        myLong returnSub = new myLong();
        returnSub.setLong(strang);

        return returnSub;       //was return strang;
    }




    public myLong multiplyLong(myLong multObj){

        ArrayList<String> arrayArray = new ArrayList<String>();

        String strangg = "";
        String newSet = "";
        String sumString = "";
        myLong sum = new myLong();
        myLong add = new myLong();

        for(int i = this.longArray.size()-1; i > -1 ; i--) {
            strangg = "";
            char carry = ' ';

            for (int j = multObj.longArray.size()-1; j > -1 ; j--) {
                int val = (Integer.valueOf(this.longArray.get(i)) * Integer.valueOf(multObj.longArray.get(j)));
                if(carry != ' '){
                    int c = carry - '0';
                    val += c;

                }
                if(val > 9 && j != 0){
                    String valString = Integer.toString(val);
                    char v = valString.charAt(1);
                    carry = valString.charAt(0);
                    strangg = v + strangg;
                }
                else {
                    strangg = String.valueOf(val) + strangg;
                }

            }
            if(i != this.longArray.size() - 1) {

                for (int x = i; x >= 0; x--) {
                    strangg = strangg.concat("0");
                }
            }

            arrayArray.add(strangg);
        }

        sum.setLong(arrayArray.get(0));

        for(int a = 1; a < arrayArray.size(); a++){
            add.setLong(arrayArray.get(a));
            sum.setLong(sum.addLong(add).toString());
        }

        if(this.negInput == "neg" && multObj.negInput == ""){
            sumString = sum.getLong();
            sumString = "-"+ sumString;
        }


        return sum;
    }


    /*
    For the = function on the calculator.
    Returns false if there's an open parenthesis
    left in the string.
     */
    public boolean parLeft(){           //helps take care of first order of operation as well
        String longString = this.longArray.toString();
        for(int i = 0; i < longString.length(); i++){
            if (longString.charAt(i) == '('){
                return false;
            }
        }
        return true;
    }

}