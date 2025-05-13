package com.example.registrovehiculo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class VehiculoDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehiculos.db";
    private static final int DB_VERSION = 1;

    public VehiculoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE vehiculos (" +
                "placa TEXT PRIMARY KEY, " +
                "marca TEXT, " +
                "color TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vehiculos");
        onCreate(db);
    }

    public void insertarVehiculo(Vehiculo v) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("placa", v.getPlaca());
        values.put("marca", v.getMarca());
        values.put("color", v.getColor());
        db.insert("vehiculos", null, values);
    }

    public Vehiculo buscarVehiculo(String placa) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("vehiculos", null, "placa=?", new String[]{placa}, null, null, null);
        if (cursor.moveToFirst()) {
            return new Vehiculo(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        return null;
    }

    public int actualizarVehiculo(Vehiculo v) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("marca", v.getMarca());
        values.put("color", v.getColor());
        return db.update("vehiculos", values, "placa=?", new String[]{v.getPlaca()});
    }

    public int eliminarVehiculo(String placa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("vehiculos", "placa=?", new String[]{placa});
    }

    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM vehiculos", null);
        while (cursor.moveToNext()) {
            lista.add(new Vehiculo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
        return lista;
    }
}
