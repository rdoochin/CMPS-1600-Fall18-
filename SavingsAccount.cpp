//
//  SavingsAccount.cpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#include "SavingsAccount.hpp"
#include "BankAccount.hpp"
#include "CheckingAccount.hpp"

/*
 a constructor for SavingAccount
 */
SavingsAccount::SavingsAccount(string n, double b, double i){
    this -> name = n;
    this -> balance = b;
    this -> interestRate = i;
}

/*
 adds interest to a SavingsAccount
 */
void SavingsAccount::addInterest(){
    this->deposit((this->getBalance()/100)*interestRate);
}
