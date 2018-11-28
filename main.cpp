//
//  main.cpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//


#include <iostream>
#include "BankAccount.hpp"
#include "CheckingAccount.hpp"
#include "SavingsAccount.hpp"

int main(int argc, const char * argv[]) {
    
    /*
    BankAccount starter("start", 0.0);              //testing BankAccount
    BankAccount advanced("ad", 500.0);
    
    starter.deposit(1000);
    advanced.withdraw(100);
    starter.withdraw(200);
    advanced.deposit(200);
    
    cout<< starter.toString();
    cout<< advanced.toString();
     */
    
    SavingsAccount newSave("save",0,1);         //testing SavingAccount and CheckingAccount
    CheckingAccount newCheck("check", 500);
    newSave.deposit(1000);
    newCheck.withdraw(100);
    newSave.withdraw(200);
    newCheck.deposit(200);
    cout<<newSave.toString();
    cout<<"\n"<<newCheck.toString();
    newSave.addInterest();
    newCheck.deductFees();
    cout<<"\n"<<newSave.toString();
    cout<<"\n"<<newCheck.toString()<<"\n";
    
    return 0;
}
