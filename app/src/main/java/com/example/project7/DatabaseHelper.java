package com.example.project7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "biodata_jurnal.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE biodata (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nama TEXT," +
                "umur TEXT," +
                "alamat TEXT)");

        db.execSQL("CREATE TABLE jurnal (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_biodata INTEGER," +
                "tanggal TEXT," +
                "isi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS biodata");
        db.execSQL("DROP TABLE IF EXISTS jurnal");
        onCreate(db);
    }

    // ================= INSERT =================

    public long insertBiodata(String nama, String umur, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("umur", umur);
        values.put("alamat", alamat);
        return db.insert("biodata", null, values);
    }

    public long insertJurnal(int idBiodata, String tanggal, String isi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_biodata", idBiodata);
        values.put("tanggal", tanggal);
        values.put("isi", isi);
        return db.insert("jurnal", null, values);
    }

    // ================= GET DATA =================

    // Ambil 1 biodata terakhir (AMAN untuk ThirdActivity)
    public Cursor getLastBiodata() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM biodata ORDER BY id DESC LIMIT 1",
                null
        );
    }

    // Ambil jurnal berdasarkan id biodata
    public Cursor getJurnalByBiodata(int idBiodata) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM jurnal WHERE id_biodata = ? ORDER BY id DESC",
                new String[]{String.valueOf(idBiodata)}
        );
    }
}
