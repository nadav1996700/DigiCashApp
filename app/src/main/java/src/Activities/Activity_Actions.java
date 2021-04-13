package src.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.src.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import src.Classes.RecyclerViewAdapter;
import src.Classes.Transaction;
import src.Utils.My_Firebase;

public class Activity_Actions extends AppCompatActivity {
    private RecyclerView transactions_list;
    private Activity activity = this;
    private ImageButton loan, saving, transfer;
    My_Firebase firebase = My_Firebase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        setValues();
        showListByDate();
    }

    private void setValues() {
        loan = findViewById(R.id.Actions_IMG_loan);
        saving = findViewById(R.id.Actions_IMG_saving);
        transfer = findViewById(R.id.Actions_IMG_transfer);
        // set RecyclerView
        setRecyclerView();
    }

    /* set settings of RecyclerView */
    private void setRecyclerView() {
        transactions_list = findViewById(R.id.Action_LST_list);
        transactions_list.setLayoutManager(new LinearLayoutManager(this));
        transactions_list.addItemDecoration(new DividerItemDecoration(transactions_list.getContext(),
                DividerItemDecoration.VERTICAL));
    }

    public void showListByDate() {
        // set reference
        String path = "/" + firebase.getAccountNumber(); // + "/Transactions";
        firebase.setReference(path);
        // get data
        firebase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Transaction> transactions = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String sender = Objects.requireNonNull(child.child("sender_name").
                            getValue()).toString();
                    Log.d("check", "sender = " + sender);
                    String type = Objects.requireNonNull(child.child("type").
                            getValue()).toString();
                    Log.d("check", "type = " + type);
                    String amount = (Objects.requireNonNull(child.child("amount").getValue())).toString();
                    Log.d("check", "amount = " + amount);
                    String date = Objects.requireNonNull(child.child("date").getValue()).toString();
                    Log.d("check", "date = " + date);
                    String time = Objects.requireNonNull(child.child("time").getValue()).toString();
                    Log.d("check", "time = " + time);
                    // create Transaction item (dataItem)
                    transactions.add(new Transaction(sender, type, amount, date, time));
                }
                transactions_list.setAdapter(new RecyclerViewAdapter(activity, transactions));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Transaction ERROR", "error occured in transaction procces");
            }
        });
    }
}