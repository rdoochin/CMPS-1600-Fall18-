public class CheckingAccount extends BankAccount {

    //a subclass of BankAccount

    public static int transactionCount =0;        //make it a protected object?   also, why errors in package?
    private static final double TRANSACTION_FEE = 3.00;

    public CheckingAccount(String n, double b) {
        super(n, b);
    }

    public void deductFees() {
        double fee = transactionCount * TRANSACTION_FEE;
        this.withdraw(fee);
        transactionCount = 0;
    }
}

