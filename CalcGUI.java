import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


public abstract class CalcGUI{

    public static class Calculator extends JFrame{
        JTextField calArea;

        Calculator(String title) {
            setTitle(title);
            setSize(300, 400);
            setLocationRelativeTo(null);
            JTextField textField = new JTextField(6);
            setVisible(true);
            // setResizable(false);

            setLayout(new GridBagLayout());
            GridBagConstraints layoutConst = new GridBagConstraints();

        /*
        instantiating the keyboard
         */
            calArea = new JTextField(16);       //where the user enters the calculation
            GridBagConstraints calA = new GridBagConstraints();
            calA.fill = GridBagConstraints.HORIZONTAL;
            calA.gridwidth = 10;
            layoutConst.gridx = 1;
            layoutConst.gridy = 2;
            calArea.setEditable(true);
            add(calArea, layoutConst);
            add(calArea, calA);

            JButton one = new JButton();
            one.setText("1");
            layoutConst.gridx = 2;
            layoutConst.gridy = 3;
            one.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "1");
                }
            });
            add(one, layoutConst);

            JButton two = new JButton();
            two.setText("2");
            layoutConst.gridx = 3;
            layoutConst.gridy = 3;
            two.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "2");
                }
            });
            add(two, layoutConst);

            JButton three = new JButton();
            three.setText("3");
            layoutConst.gridx = 4;
            layoutConst.gridy = 3;
            three.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "3");
                }
            });
            add(three, layoutConst);

            JButton four = new JButton();
            four.setText("4");
            layoutConst.gridx = 2;
            layoutConst.gridy = 4;
            four.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "4");
                }
            });
            add(four, layoutConst);

            JButton five = new JButton();
            five.setText("5");
            layoutConst.gridx = 3;
            layoutConst.gridy = 4;
            five.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "5");
                }
            });
            add(five, layoutConst);

            JButton six = new JButton();
            six.setText("6");
            layoutConst.gridx = 4;
            layoutConst.gridy = 4;
            six.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "6");
                }
            });
            add(six, layoutConst);

            JButton seven = new JButton();
            seven.setText("7");
            layoutConst.gridx = 2;
            layoutConst.gridy = 5;
            seven.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "7");
                }
            });
            add(seven, layoutConst);

            JButton eight = new JButton();
            eight.setText("8");
            layoutConst.gridx = 3;
            layoutConst.gridy = 5;
            eight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "8");
                }
            });
            add(eight, layoutConst);

            JButton nine = new JButton();
            nine.setText("9");
            layoutConst.gridx = 4;
            layoutConst.gridy = 5;
            nine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "9");
                }
            });
            add(nine, layoutConst);

            JButton zero = new JButton();
            zero.setText("0");
            layoutConst.gridx = 2;
            layoutConst.gridy = 6;
            zero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "0");
                }
            });
            add(zero, layoutConst);

            JButton add = new JButton();
            add.setText("+");
            layoutConst.gridx = 2;
            layoutConst.gridy = 7;
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "+");
                }
            });
            add(add, layoutConst);

            JButton subtract = new JButton();
            subtract.setText("-");
            layoutConst.gridx = 3;
            layoutConst.gridy = 7;
            subtract.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "-");
                }
            });
            add(subtract, layoutConst);

            JButton mult = new JButton();
            mult.setText("*");
            layoutConst.gridx = 4;
            layoutConst.gridy = 7;
            mult.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "*");
                }
            });
            add(mult, layoutConst);

            JButton equal = new JButton();
            equal.setText("=");
            layoutConst.gridx = 4;
            layoutConst.gridy = 8;
            add(equal, layoutConst);
            equal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isValidInput(calArea)){
                        calArea.setText("Invalid Input.");
                    }
                    String input = calArea.getText();
                    Calculate calInput = new Calculate(input);
                    calArea.setText(calInput.calculate());

                }
            });

            JButton openPar = new JButton();
            openPar.setText("(");
            layoutConst.gridx = 3;
            layoutConst.gridy = 6;
            openPar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + "(");
                }
            });
            add(openPar, layoutConst);

            JButton closePar = new JButton();
            closePar.setText(")");
            layoutConst.gridx = 4;
            layoutConst.gridy = 6;
            closePar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText(calArea.getText() + ")");
                }
            });
            add(closePar, layoutConst);

            JButton clear = new JButton();
            clear.setText("clear");
            layoutConst.gridx = 2;
            layoutConst.gridy = 8;
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calArea.setText("");
                }
            });
            add(clear, layoutConst);


        }

        /*
      method for the equals operator
       */

        public boolean isValidInput(JTextField inputField) {
            String newCal = inputField.getText();
            int count1 = 0;
            int count2 = 0;
            int answer = 0;

            for (int i = 0; i < newCal.length(); i++) {

                /*
                Getting the number of open and close parenthesis.
                 */
                if (newCal.charAt(i) == '(') {
                    count1 += 1;
                }
                if (newCal.charAt(i) == ')') {
                    count2 += 1;
                }

                /*
                Checking to see if operators are next to
                each other.
                 */
                if((newCal.charAt(i) == '*' || newCal.charAt(i) == '+' || newCal.charAt(i) == '-')
                && (newCal.charAt(i+1) == '*' || newCal.charAt(i+1) == '+' || newCal.charAt(i+1) == '-')){
                    return false;
                }
            }

            if(count1 != count2){
                return false;
            }

             /*
                Checking to see if an operator is the first char.
                 */
            if(newCal.charAt(0) == '*' || newCal.charAt(0) == '+' ||
                    newCal.charAt(0) == '-' || newCal.charAt(0) == ')'){
                return false;
            }
            if(newCal.charAt(newCal.length()-1) == '('){
                return false;
            }

            else {
                return true;
            }

        }

        public static void main(String[] args){
            Calculator myCalc = new Calculator("Rebekah's Calculator");
            myCalc.setResizable(false);
            myCalc.setVisible(true);
            myCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }

}
