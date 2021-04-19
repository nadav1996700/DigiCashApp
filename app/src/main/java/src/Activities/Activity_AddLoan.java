package src.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.src.R;

public class Activity_AddLoan extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan);

        setValues();

        back.setOnClickListener(view -> { finish(); });
    }

    private void setValues() {
        back = findViewById(R.id.AddLoan_BTN_back);
    }
}