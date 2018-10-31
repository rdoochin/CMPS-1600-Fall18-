public class Calculate extends myLong {
    String nums;
    String beforeSlice = "";
    String afterSlice = "";

    public Calculate(String stringName){
        nums = stringName;
    }

    public void setString(String newString){
        this.nums = newString;
    }

    /*
   For the = function on the calculator.
   Returns false if there's an open parenthesis
   left in the string.
    */
    public boolean parLeft(){
        //System.out.println(nums);
        for(int i = this.nums.length()-1; i > -1; i--){

            if (this.nums.charAt(i) == '('){
                return false;
            }
        }
        return true;
    }

    public String calculate(){
        Calculate subCalc = new Calculate("");
        String calString = "";
        String orderOpRet = "";

        int index = 0;
        int backIndex = this.nums.length()-1;

        if(this.parLeft() == false){
            for(int i = this.nums.length()-1; i > -1; i--) {
                if(this.nums.charAt(i) == '('){
                    index = i;
                    break;
                }
            }

            if(index != 0) {
                beforeSlice = this.nums.substring(0, index);
            }

            for(int j = index; j < this.nums.length(); j++){
                if(this.nums.charAt(j) == ')'){
                    backIndex = j;
                    break;
                }
            }

            if(backIndex != this.nums.length()-1){
                afterSlice = this.nums.substring(backIndex+1);
            }

            calString = this.nums.substring(index+1, backIndex);

            /*
            if(calString.contains("(")){
                calString = calString.concat(")");
                //System.out.println(calString);
                subCalc.setString(calString);
                calString = subCalc.calculate();
                System.out.println(calString);
                System.out.println(beforeSlice);
            }   */

            subCalc.setString(calString);

            orderOpRet = subCalc.orderOp();
            orderOpRet = beforeSlice + orderOpRet + afterSlice;

            if(orderOpRet.contains("+") || orderOpRet.contains("*") || orderOpRet.contains("-")){
                subCalc.setString(orderOpRet);
                orderOpRet = subCalc.calculate();
            }

        }
        else {
            orderOpRet = this.nums;
        }

        if(beforeSlice.contains(" ")){
            beforeSlice = beforeSlice.replace(" ", "");
        }
        if(afterSlice.contains(" ")){
            afterSlice = afterSlice.replace(" ", "");
        }

        subCalc.setString(orderOpRet);
        orderOpRet = subCalc.orderOp();

        if(orderOpRet.contains("+") || orderOpRet.contains("*") || orderOpRet.contains("-")){
            subCalc.setString(orderOpRet);
            orderOpRet = subCalc.orderOp();
        }

        return orderOpRet;
    }





    /*
    Inputting a string with just numbers and operators, and
    outputting a solution string.
     */
    public String orderOp() {

        myLong long1 = new myLong();
        myLong long2 = new myLong();
        myLong solution = new myLong();

        Calculate calcOb = new Calculate("");

        String returnString = "";
        String leftOver1 = "";
        String leftOver2 = "";

        String setString = "";
        String setString2 = "";

        if (!this.nums.contains("*") && !this.nums.contains("+") && !this.nums.contains("-")) {
            return this.nums;
        }

        if (this.nums.contains("*")) {

            int indexMult = this.nums.indexOf("*");     //gets index of operator

            for (int i = indexMult - 1; i > -1; i--) {
                if (this.nums.charAt(i) == '*' || this.nums.charAt(i) == '+' ||
                        this.nums.charAt(i) == '-') {
                    leftOver1 = this.nums.substring(0, i+1);
                    break;
                }
                String newSet = setString;
                setString = Character.toString(this.nums.charAt(i)).concat(newSet);
                long1.setLong(setString);       // the first myLong to put into multiplyLong()
            }

            for (int j = indexMult + 1; j < this.nums.length(); j++) {
                if (this.nums.charAt(j) == '*' || this.nums.charAt(j) == '+' ||
                        this.nums.charAt(j) == '-') {
                    leftOver2 = this.nums.substring(j);
                    break;
                }
                String newSet2 = setString2;
                setString2 = newSet2.concat(Character.toString(this.nums.charAt(j)));

                if(!setString2.contains(")")) {
                    long2.setLong(setString2);
                }
            }

            solution.setLong(long1.multiplyLong(long2).getLong());
            returnString = solution.getLong();
            returnString = leftOver1 + returnString + leftOver2;
            return returnString;
        }

        int indexAdd = 0;
        int indexSub = 0;


        if (this.nums.contains(" ")) {
            this.nums = this.nums.replace(" ", "");
        }


        if (this.nums.contains("+") || this.nums.contains("-")) {
            if (this.nums.contains("+")) {
                indexAdd = this.nums.indexOf("+");
            }

            if (this.nums.contains("-")) {
                indexSub = this.nums.indexOf("-");
            }

            if (indexAdd != 0) {
                for (int i = indexAdd - 1; i > -1; i--) {
                    if (this.nums.charAt(i) == '*' || this.nums.charAt(i) == '+' ||
                            this.nums.charAt(i) == '-') {
                        leftOver1 = this.nums.substring(0, i);
                        break;
                    }
                    setString = Character.toString(this.nums.charAt(i)).concat(setString);

                    long1.setLong(setString);
                }

                for (int j = indexAdd + 1; j < this.nums.length(); j++) {
                    if (this.nums.charAt(j) == '*' || this.nums.charAt(j) == '+' ||
                            this.nums.charAt(j) == '-') {
                        leftOver2 = this.nums.substring(j);
                        break;
                    }
                    setString2 = setString2.concat(Character.toString(this.nums.charAt(j)));
                    long2.setLong(setString2);
                }

                solution.setLong(long1.addLong(long2).getLong());
                returnString = solution.getLong();

            }

            /*
            Doing the subtraction first.
             */
            else {

                for (int i = indexSub - 1; i > -1; i--) {
                    if (this.nums.charAt(i) == '*' || this.nums.charAt(i) == '+' ||  //dont need the * ?
                            this.nums.charAt(i) == '-') {
                        leftOver1 = this.nums.substring(0, i);
                        break;
                    }
                    setString = Character.toString(this.nums.charAt(i)).concat(setString);
                    long1.setLong(setString);
                }

                for (int j = indexSub + 1; j < this.nums.length(); j++) {
                    if (this.nums.charAt(j) == '*' || this.nums.charAt(j) == '+' ||
                            this.nums.charAt(j) == '-') {
                        leftOver2 = this.nums.substring(j);
                        break;
                    }

                    setString2 = setString2.concat(Character.toString(this.nums.charAt(j)));
                    long2.setLong(setString2);
                }


                solution.setLong(long1.subtractLong(long2).getLong());
                returnString = solution.getLong();
                return returnString;
            }
        }

        if(!leftOver1.equals("")){
            returnString = leftOver1 + returnString;
        }
        if(!leftOver2.equals("")){
            returnString = returnString.concat(leftOver2);
        }

        if(returnString.contains("*") ||returnString.contains("+") || returnString.contains("-")){
            calcOb.setString(returnString);
            calcOb.orderOp();
        }
        return returnString;
    }

}
