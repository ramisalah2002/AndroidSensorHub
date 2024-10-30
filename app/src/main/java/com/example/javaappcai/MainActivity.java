package com.example.javaappcai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText editNom, editPrenom;
    private Button buttonOk, buttonOpenCam;
    private TextView textViewResult;
    private ImageView imageView;

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<Bitmap> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editNom = findViewById(R.id.input_nom);
        editPrenom = findViewById(R.id.input_prenom);
        buttonOk = findViewById(R.id.button);
        buttonOpenCam = findViewById(R.id.button_open_cam);
        textViewResult = findViewById(R.id.label_text);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerView);

        // Setup RecyclerView for displaying images
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imageAdapter = new ImageAdapter(imageList, new ImageAdapter.OnImageClickListener() {
            @Override
            public void onDeleteClick(int position) {
                // Remove the image from the list
                imageList.remove(position);
                imageAdapter.notifyItemRemoved(position);
            }
        });
        recyclerView.setAdapter(imageAdapter);

        // Button click listener for Ok button
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editNom.getText().toString();
                String prenom = editPrenom.getText().toString();

                if (!nom.isEmpty() && !prenom.isEmpty()) {
                    String result = "Salut, " + nom + " " + prenom + "!";
                    textViewResult.setText(result);
                } else {
                    textViewResult.setText("Please enter both Nom and Prénom");
                }
            }
        });

        // Button click listener for Open Cam button
        buttonOpenCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the camera
                openCamera();
            }
        });
    }

    // Method to open the camera using an Intent
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // Handle the result from the camera intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the photo as a Bitmap from the intent data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Display the photo in the main ImageView
            imageView.setImageBitmap(imageBitmap);

            // Add the photo to the list and update the RecyclerView
            imageList.add(imageBitmap);
            imageAdapter.notifyItemInserted(imageList.size() - 1);
        }
    }
}
