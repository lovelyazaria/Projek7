package com.example.project7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    EditText etJurnal;
    Button btnSimpanJurnal;
    DatabaseHelper db;
    long idBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etJurnal = findViewById(R.id.etJurnal);
        btnSimpanJurnal = findViewById(R.id.btnSimpanJurnal);
        db = new DatabaseHelper(this);

        idBiodata = getIntent().getLongExtra("ID_BIODATA", -1);

        btnSimpanJurnal.setOnClickListener(v -> {

            String tanggal = new SimpleDateFormat(
                    "dd-MM-yyyy", Locale.getDefault()
            ).format(new Date());

            db.insertJurnal((int) idBiodata, tanggal, etJurnal.getText().toString());

            Toast.makeText(this,
                    "Jurnal disimpan",
                    Toast.LENGTH_SHORT).show();

            // PINDAH KE THIRD ACTIVITY
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            intent.putExtra("ID_BIODATA", idBiodata);
            startActivity(intent);
        });
    }
}
