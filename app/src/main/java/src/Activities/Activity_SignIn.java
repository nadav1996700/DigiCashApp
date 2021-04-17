package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.src.R;
import com.google.firebase.auth.FirebaseAuth;

import src.Utils.My_Firebase;

public class Activity_SignIn extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView error_message;
    private Button sign_in;
    private TextView register;
    private TextView forgotPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    My_Firebase firebase = My_Firebase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setValues();

        register.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Register.class);
            startActivity(intent);
        });

        sign_in.setOnClickListener(view -> {
            checkCredentials();
        });

        forgotPassword.setOnClickListener(view -> {
            sendPassword();
        });
    }

    // check credentials and move to actions page
    private void checkCredentials() {
        if(email.getText() != null && !email.getText().toString().isEmpty() &&
        password.getText() != null && !password.getText().toString().isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(
                    email.getText().toString()
                  , password.getText().toString())
                  .addOnCompleteListener(this, task -> {
                      if(task.isSuccessful()) {
                          // Sign in success
                          Intent intent = new Intent(Activity_SignIn.this, Activity_Actions.class);
                          startActivity(intent);
                          error_message.setVisibility(View.GONE);
                          progressBar.setVisibility(View.GONE);
                      }
                      else {
                          error_message.setVisibility(View.VISIBLE);
                          progressBar.setVisibility(View.GONE);
                      }
                  });
        }
    }

    private void sendPassword() {
        // set dialog
        final EditText resetPassword  = new EditText(Activity_SignIn.this);
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(Activity_SignIn.this);

        // custom the dialog
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Enter your email to reset the password");
        passwordResetDialog.setView(resetPassword);

        // set Listener
        passwordResetDialog.setPositiveButton("Send", (dialogInterface, i) ->
                mAuth.sendPasswordResetEmail(resetPassword.getText().toString().trim()))
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

        }).show();
    }

    // initialize variables
    private void setValues() {
        email = findViewById(R.id.SignIn_EDT_email);
        password = findViewById(R.id.SignIn_EDT_password);
        error_message = findViewById(R.id.SignIn_TV_error);
        sign_in = findViewById(R.id.SignIn_BTN_login);
        register = findViewById(R.id.SignIn_TV_Register);
        forgotPassword = findViewById(R.id.SignIn_TV_forgot_password);
        progressBar = findViewById(R.id.SignIn_PB_progressBar);
        mAuth = FirebaseAuth.getInstance();
    }
}
