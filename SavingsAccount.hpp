//
//  SavingsAccount.hpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#ifndef SavingsAccount_hpp
#define SavingsAccount_hpp

#include "BankAccount.hpp"
#include "CheckingAccount.hpp"
#include <stdio.h>
#include <iostream>

class SavingsAccount : public BankAccount{
public:
    SavingsAccount(string n, double b, double i);
    
    void addInterest();
    
private:
    double interestRate;
};

#endif /* SavingsAccount_hpp */
