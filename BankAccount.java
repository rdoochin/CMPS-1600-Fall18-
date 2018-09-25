public class BankAccount {

    protected String name;
    protected double balance;

    public BankAccount(String n, double b){
        name = n;
        balance = b;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    //a method to add to the bankAccount
    public double deposit(double depNum){
        balance += depNum;
        CheckingAccount.transactionCount += 1;
        return balance;
    }

    //a method to take out of the bankAccount
    public double withdraw(double wdNum){
        balance -= wdNum;
        CheckingAccount.transactionCount += 1;
        return balance;
    }

    //a method to make a transfer from one account to another
    public void transfer(BankAccount originAccount, double tranNum){
        originAccount.balance += tranNum;
        this.balance -= tranNum;
    }

    public String toString(){
        return name + ": $" + balance;
    }

}

