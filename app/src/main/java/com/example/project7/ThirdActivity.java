package com.example.project7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    TextView tvNama, tvUmur, tvTTL, tvAlamat, tvJurnal;
    DatabaseHelper db;
    long idBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvNama = findViewById(R.id.tvNama);
        tvUmur = findViewById(R.id.tvUmur);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvJurnal = findViewById(R.id.tvJurnal);

        db = new DatabaseHelper(this);
        idBiodata = getIntent().getLongExtra("ID_BIODATA", -1);

        tampilkanData();
    }

    private void tampilkanData() {
        SQLiteDatabase database = db.getReadableDatabase();

        // Ambil biodata
        Cursor biodata = database.rawQuery(
                "SELECT * FROM biodata WHERE id = ?",
                new String[]{String.valueOf(idBiodata)}
        );

        if (biodata.moveToFirst()) {
            tvNama.setText("Nama : " + biodata.getString(1));
            tvUmur.setText("Umur : " + biodata.getString(2));
            tvAlamat.setText("Alamat : " + biodata.getString(3));
        }

        // Ambil jurnal
        Cursor jurnal = database.rawQuery(
                "SELECT tanggal, isi FROM jurnal WHERE id_biodata = ?",
                new String[]{String.valueOf(idBiodata)}
        );

        StringBuilder isiJurnal = new StringBuilder();

        while (jurnal.moveToNext()) {
            isiJurnal.append("Tanggal: ")
                    .append(jurnal.getString(0))
                    .append("\n")
                    .append(jurnal.getString(1))
                    .append("\n\n");
        }

        tvJurnal.setText(isiJurnal.toString());
    }
}

