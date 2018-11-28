//
//  CheckingAccount.hpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#ifndef CheckingAccount_hpp
#define CheckingAccount_hpp

#include <stdio.h>
#include "BankAccount.hpp"
#include "SavingsAccount.hpp"
#include <iostream>
#include <string>
class CheckingAccount : public BankAccount{
public:
    CheckingAccount(string n, double b);
    
    void withdraw(double a);
    void deposit(double a);
    
    void deductFees();
private:
    int transactionCount;
    const double TRANSACTION_FEE = 3.0;
    
};

#endif /* CheckingAccount_hpp */
