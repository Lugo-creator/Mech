package com.example.mobilecarcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMechanicActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mechanic);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        EditText fullName = findViewById(R.id.fullname);
        EditText emailTxt = findViewById(R.id.email);
        EditText numberTxt = findViewById(R.id.phone);
        EditText stationTxt = findViewById(R.id.station);
        EditText specializationTxt = findViewById(R.id.specialization);
        Button add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString();
                String email = emailTxt.getText().toString();
                String number = numberTxt.getText().toString();
                String station = stationTxt.getText().toString();
                String specialization = specializationTxt.getText().toString();

                if (name.isEmpty() || email.isEmpty() || number.isEmpty() || station.isEmpty() || specialization.isEmpty()) {
                    Toast.makeText(AddMechanicActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    saveMechanicToFirestore(name, email, number, station, specialization);
                }
            }
        });
    }

    private void saveMechanicToFirestore(String name, String email, String phone, String station, String specialization) {
        // Create a HashMap to store mechanic details
        Map<String, Object> mechanic = new HashMap<>();
        mechanic.put("name", name);
        mechanic.put("email", email);
        mechanic.put("phone", phone);
        mechanic.put("station", station);
        mechanic.put("specialization", specialization);

        // Add data to Firestore
        db.collection("mechanics")
                .add(mechanic)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddMechanicActivity.this, "Mechanic added successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after successful addition
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddMechanicActivity.this, "Failed to add mechanic: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
