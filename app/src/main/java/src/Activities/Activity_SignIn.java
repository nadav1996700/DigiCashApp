package src.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.src.R;

import src.Utils.My_Firebase;

public class Activity_SignIn extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView error_message;
    private Button sign_in;
    private Button register;
    My_Firebase firebase = My_Firebase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setValues();
    }

    private void setValues() {
        username = findViewById(R.id.SignIn_EDT_username);
        password = findViewById(R.id.SignIn_EDT_password);
        error_message = findViewById(R.id.SignIn_TV_error);
        sign_in = findViewById(R.id.SignIn_BTN_login);
        register = findViewById(R.id.SignIn_BTN_openAccount);
    }
}
