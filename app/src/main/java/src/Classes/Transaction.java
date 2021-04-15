package src.Classes;

import androidx.annotation.NonNull;

public class Transaction {
    private String sender;
    private String amount;
    private String type;
    private String date;
    private String time;

    public Transaction(String sender, String type, String amount, String date, String time) {
        this.sender = sender;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Transfer from " + sender;
    }
}
