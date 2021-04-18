package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.src.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import src.Classes.BankAccount;
import src.Classes.Customer;
import src.Utils.My_Firebase;

public class Activity_Register extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText id;
    private EditText birthday;
    private EditText address;
    private EditText phone;
    private EditText email;
    private String password;
    private Button send_request;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private boolean register_flag = true;
    My_Firebase firebase = My_Firebase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setValues();

        send_request.setOnClickListener(view -> {
            if(checkData()) {
                // regiter client using firebase auth and
                register();
                if(!register_flag) {
                    Toast.makeText(this, "Failed Registration", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void register() {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Customer owner = new Customer(first_name.getText().toString(),
                                 last_name.getText().toString(),
                                 id.getText().toString(),
                                 birthday.getText().toString(),
                                 address.getText().toString(),
                                 phone.getText().toString(),
                                email.getText().toString());
                        BankAccount bankAccount = new BankAccount(owner);
                        firebase.setReference("Accounts");
                        firebase.getReference()
                                .child(String.valueOf(bankAccount.getAccount_number()))
                                .setValue(bankAccount).addOnCompleteListener(task1 -> {
                                      if(task1.isSuccessful()) {
                                          CreateUser(owner.getEmail(), bankAccount.getAccount_number());
                                          Intent intent = new Intent(Activity_Register.this, Activity_SignIn.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                      else {
                                          register_flag = false;
                                          progressBar.setVisibility(View.GONE);
                                      }
                                });
                    }
                    else {
                        register_flag = false;
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    // add user to firebase
    private void CreateUser(String email, int account_number) {
        String formatted_email = email.replace('.', '/');
        firebase.setReference("Users");
        firebase.getReference().child(formatted_email)
                .setValue(String.valueOf(account_number)).addOnCompleteListener(task1 -> {
            if(task1.isSuccessful()) {
                Log.d("log", "ADD USER WITH ACCOUNT NUMMBER " + account_number);
            }
        });
    }

    // check if data is valid
    private boolean checkData() {
        boolean flag = true;
        final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
        final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        if(!DATE_PATTERN.matcher(birthday.getText().toString()).matches()) {
            birthday.setError("date should be like '05/04/1996'");
            flag = false;
        }
        if(!EMAIL_PATTERN.matcher(email.getText().toString()).matches()) {
            email.setError("wrong email");
            flag = false;
        }
        if(first_name.getText().toString().length() == 0) {
            first_name.setError("empty field!");
            flag = false;
        }
        if(last_name.getText().toString().length() == 0) {
            last_name.setError("empty field!");
            flag = false;
        }
        if(id.getText().toString().length() != 9) {
            id.setError("id must contain 9 digits!");
            flag = false;
        }
        if(address.getText().toString().length() == 0) {
            address.setError("empty field!");
            flag = false;
        }
        if(phone.getText().toString().length() < 9) {
            phone.setError("phone number is not correct!");
            flag = false;
        }
        password = id.getText().toString();
        return flag;
    }

    private void setValues() {
        first_name = findViewById(R.id.Register_EDT_firstName);
        last_name = findViewById(R.id.Register_EDT_lastName);
        id = findViewById(R.id.Register_EDT_id);
        birthday = findViewById(R.id.Register_EDT_birthDay);
        address = findViewById(R.id.Register_EDT_address);
        phone = findViewById(R.id.Register_EDT_phone);
        send_request = findViewById(R.id.Register_BTN_openAccount);
        email = findViewById(R.id.Register_EDT_email);
        progressBar = findViewById(R.id.Register_PB_progressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}