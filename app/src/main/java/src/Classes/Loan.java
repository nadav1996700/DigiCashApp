package src.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
    private static final double INTEREST = 2.0;
    private int loan_number;
    private int loan_amount;
    private int months;
    private String cause;
    private String request_date;
    private String status;
    private double monthly_payment;

    public Loan(int loan_amount, int months, String cause, String request_date) {
        this.loan_number = CreateLoanNumber();
        this.loan_amount = loan_amount;
        this.months = months;
        this.cause = cause;
        this.status = "wating";
        this.monthly_payment = loan_amount*INTEREST / months;
        this.request_date = request_date;

    }

    // create unique loan number base on date and time
    private int CreateLoanNumber() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        return Integer.parseInt(formatter.format(date)) + Integer.parseInt(time.format(date));
    }

    public int getLoan_number() {
        return loan_number;
    }

    public void setLoan_number(int loan_number) {
        this.loan_number = loan_number;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public int getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(int loan_amount) {
        this.loan_amount = loan_amount;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(double monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    @Override
    public String toString() {
        return
                "loan number: " + loan_number +
                ", loan_amount: " + loan_amount +
                ", months: " + months +
                ", cause: " + cause +
                ", request_date: " + request_date +
                ", status: " + status +
                ", monthly_payment: " + monthly_payment;
    }
}
