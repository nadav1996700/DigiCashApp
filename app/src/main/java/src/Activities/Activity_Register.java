package src.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.src.R;

import java.util.regex.Pattern;

import src.Utils.My_Firebase;

public class Activity_Register extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText id;
    private EditText birthday;
    private EditText address;
    private EditText phone;
    private EditText email;
    private Button send_request;
    My_Firebase firebase = My_Firebase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setValues();

        send_request.setOnClickListener(view -> {
            if(checkData()) {
                // regiter client using firebase auth and
                // move to success page
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
    }
}