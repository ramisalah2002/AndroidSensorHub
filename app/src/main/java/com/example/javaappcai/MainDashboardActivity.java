package com.example.javaappcai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button stepCounterButton = findViewById(R.id.step_counter);
        Button cameraButton = findViewById(R.id.camera);

        stepCounterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainDashboardActivity.this, StepCounterActivity.class);
            startActivity(intent);
        });

        cameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainDashboardActivity.this, CameraActivity.class);
            startActivity(intent);
        });
    }
}

