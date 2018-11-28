//
//  CheckingAccount.cpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#include "CheckingAccount.hpp"
#include "BankAccount.hpp"

/*
 a constructor for CheckingAccount
 */
CheckingAccount::CheckingAccount(string n, double b){
    this-> name = n;
    this -> balance = b;
}

/*
 calls the withdraw method from BankAccount
 */
void CheckingAccount::withdraw(double a){
    BankAccount::withdraw(a);
    transactionCount++;
}

/*
 calls the deposit method from BankAccount
 */
void CheckingAccount::deposit(double a){
    BankAccount::deposit(a);
    transactionCount++;
}

/*
 deducts the transaction fees from a CheckingAccount
 */
void CheckingAccount::deductFees(){
    withdraw(transactionCount*TRANSACTION_FEE);
    transactionCount = 0;
}
