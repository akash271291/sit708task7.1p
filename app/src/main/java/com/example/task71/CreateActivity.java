package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.task71.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = binding.editTextDate.getText().toString();
                String description = binding.editTextDescription.getText().toString();
                String location = binding.editTextLocation.getText().toString();
                String name = binding.editTextName.getText().toString();
                String phone = binding.editTextPhone.getText().toString();
                String lostFound = "";
                //check if item radio button is lost or found
                if(binding.radioButtonLost.isChecked()) {
                    lostFound = "lost";
                } else if (binding.radioButtonFound.isChecked()) {
                    lostFound = "found";
                }

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(description) ||
                        TextUtils.isEmpty(date) || TextUtils.isEmpty(location) || TextUtils.isEmpty(lostFound)) {
                    Toast.makeText(CreateActivity.this, "Please fill in all information", Toast.LENGTH_SHORT).show();
                } else {
                    Item newItem = new Item(0, name, lostFound, phone, description, date, location);
                    databaseHelper.onInsert(newItem);
                    Intent newIntent = new Intent(CreateActivity.this, MainActivity.class);
                    startActivityForResult(newIntent, 1);
                    finish();
                }


            }
        });

    }
}