package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.src.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import src.Classes.Loan;

public class Activity_Loan extends AppCompatActivity {
        private ImageButton back;
        private FloatingActionButton add_loan;
        private ArrayList<Loan> loans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        setValues();

        back.setOnClickListener(view -> { finish(); });
        add_loan.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_AddLoan.class);
            startActivity(intent);
        });
    }

    private void setValues() {
        back = findViewById(R.id.Loan_BTN_back);
        add_loan = findViewById(R.id.Loan_BTN_addLoan);
    }
}