package com.example.project7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etUmur, etAlamat;
    Button btnLanjut;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etUmur = findViewById(R.id.etUmur);
        etAlamat = findViewById(R.id.etAlamat);
        btnLanjut = findViewById(R.id.btnLanjut);

        db = new DatabaseHelper(this);

        btnLanjut.setOnClickListener(v -> {
            long idBiodata = db.insertBiodata(
                    etNama.getText().toString(),
                    etUmur.getText().toString(),
                    etAlamat.getText().toString()
            );

            if (idBiodata != -1) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("ID_BIODATA", idBiodata);
                startActivity(intent);
            }
        });
    }
}
