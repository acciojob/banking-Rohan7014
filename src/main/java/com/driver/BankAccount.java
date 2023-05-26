package com.driver;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BankAccount {
    private String name;
    private double balance;
    private double minBalance;
    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }
    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum<0 || digits*9>sum){
            throw new Exception("Account Number can not be generated");
        }
        String acNo="";
        int remaining=sum;
        for(int i=0;i<digits;i++){
            int dig=Math.min(9,remaining);
            acNo+=dig;
            remaining-=dig;
        }
        return acNo;
    }
    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
    }
    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance<amount || this.balance-amount<this.minBalance){
            throw new Exception("Insufficient Balance");
        }
        this.balance-=amount;
    }
}