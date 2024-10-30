package com.example.javaappcai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class SignupActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button signupButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DBHelper(this);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("SignupActivity", "Email: " + email + " | Password: " + password);

        boolean isInserted = dbHelper.insertUser(email, password);

        if (isInserted) {
            Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
            Log.e("SignupActivity", "Signup failed: Unable to insert user into database.");
        }
    }


}
