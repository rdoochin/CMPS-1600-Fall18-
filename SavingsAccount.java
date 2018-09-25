public class SavingsAccount extends BankAccount{

    //a subclass of BankAccount

        private double interest;

        public SavingsAccount(String n, double b, double intRate) {
            super(n,b);
            interest = intRate;

        }

        //a method to add an amount of to the balance in a BankAccount
        public void addInterest(){
            double add;
            add = (balance/100) * interest;
            balance += add;
        }

    }
