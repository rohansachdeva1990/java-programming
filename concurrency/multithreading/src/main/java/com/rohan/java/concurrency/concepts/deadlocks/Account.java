package com.rohan.java.concurrency.concepts.deadlocks;

/**
 * Created by rohan on 18/03/2017.
 */
public class Account {

    private int balance = 10000;

    public static void transfer(Account a1, Account a2, int amount) {
        a1.withdraw(amount);
        a2.deposit(amount);
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
