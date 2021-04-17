package src.Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BankAccount {
    private int account_number;
    private Customer owner;
    private double available_balance = 0;
    private double current_balance = 0;
    private ArrayList<SavingAccount> savingAccounts;
    private ArrayList<Loan> loans;

    public BankAccount() {

    }

    public BankAccount(Customer owner) {
        this.account_number = CreateAccountNumber();
        this.owner = owner;
        savingAccounts = new ArrayList<>();
        loans = new ArrayList<>();
    }

    // create unique account number base on date and time
    private int CreateAccountNumber() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        return Integer.parseInt(formatter.format(date)) + Integer.parseInt(time.format(date));
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public double getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(double available_balance) {
        this.available_balance = available_balance;
    }

    public double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(double current_balance) {
        this.current_balance = current_balance;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
