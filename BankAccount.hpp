//
//  BankAccount.hpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#ifndef BankAccount_hpp
#define BankAccount_hpp

#include <stdio.h>
#include <string>
#include <iostream>
//#include <cstring>

using namespace std;

class BankAccount{
public:
    BankAccount();
    BankAccount(string n, double b);
    
    string getName();
    double getBalance();
    string toString();
    
    virtual void deposit(double a);
    virtual void withdraw(double a);
    
    string name;
    double balance;
};

#endif /* BankAccount_hpp */
