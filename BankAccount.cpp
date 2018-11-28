//
//  BankAccount.cpp
//  BankAccount
//
//  Created by Rebekah on 11/20/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//
//#include <cstring>
#include "BankAccount.hpp"
#include <string>
#include <iostream>

using namespace std;

BankAccount :: BankAccount(){
    this -> name = "";
    this -> balance = 0.0;
}

BankAccount :: BankAccount(string n, double b){
    this -> name = n;
    this -> balance = b;
    
}

string BankAccount :: getName(){                      //returns the name of a BankAccount
    return name;
}

double BankAccount:: getBalance(){                    //returns the balance of a BankAccount
    return balance;
}

string BankAccount::toString(){                       //returns the name and balance of a BanckAccount
    std:: string newDoub = std:: to_string(this -> getBalance());
    string retStr = "The balance of " + this->getName() + " is " + newDoub + ".";
    return retStr;
}

/*
 a method to deposite money into a bank account
 */
void BankAccount::deposit(double a){
    this -> balance = this -> balance + a;
}

/*
 a method to withdraw money into a bank account
 */
void BankAccount::withdraw(double a){
    this -> balance = this -> balance - a;
}
