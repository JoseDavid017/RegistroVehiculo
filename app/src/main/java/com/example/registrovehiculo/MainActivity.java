package com.example.registrovehiculo;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editPlaca, editMarca, editColor;
    TextView textResultado;
    VehiculoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPlaca = findViewById(R.id.editPlaca);
        editMarca = findViewById(R.id.editMarca);
        editColor = findViewById(R.id.editColor);
        textResultado = findViewById(R.id.textResultado);
        dbHelper = new VehiculoDbHelper(this);

        findViewById(R.id.btnRegistrar).setOnClickListener(v -> registrarVehiculo());
        findViewById(R.id.btnBuscar).setOnClickListener(v -> buscarVehiculo());
        findViewById(R.id.btnActualizar).setOnClickListener(v -> actualizarVehiculo());
        findViewById(R.id.btnEliminar).setOnClickListener(v -> eliminarVehiculo());
        findViewById(R.id.btnListar).setOnClickListener(v -> listarVehiculos());
    }

    private void registrarVehiculo() {
        Vehiculo v = new Vehiculo(
                editPlaca.getText().toString(),
                editMarca.getText().toString(),
                editColor.getText().toString()
        );
        dbHelper.insertarVehiculo(v);
        textResultado.setText("Vehículo registrado.");
    }

    private void buscarVehiculo() {
        Vehiculo v = dbHelper.buscarVehiculo(editPlaca.getText().toString());
        if (v != null) {


            String info = "Placa: " + v.getPlaca() +
                    ", Marca: " + v.getMarca() +
                    ", Color: " + v.getColor();
            textResultado.setText("VEHICULO ENCONTRADO:");
            textResultado.setText(info);
        } else {
            textResultado.setText("Vehículo no encontrado.");
        }
    }

    private void actualizarVehiculo() {
        Vehiculo v = new Vehiculo(
                editPlaca.getText().toString(),
                editMarca.getText().toString(),
                editColor.getText().toString()
        );
        int filas = dbHelper.actualizarVehiculo(v);
        textResultado.setText(filas > 0 ? "Vehículo actualizado." : "No se encontró el vehículo.");
    }

    private void eliminarVehiculo() {
        int filas = dbHelper.eliminarVehiculo(editPlaca.getText().toString());
        textResultado.setText(filas > 0 ? "Vehículo eliminado." : "No se encontró el vehículo.");
    }

    private void listarVehiculos() {
        List<Vehiculo> lista = dbHelper.listarVehiculos();
        StringBuilder sb = new StringBuilder();
        for (Vehiculo v : lista) {
            sb.append("Placa: ").append(v.getPlaca())
                    .append(", Marca: ").append(v.getMarca())
                    .append(", Color: ").append(v.getColor())
                    .append("\n");
        }
        textResultado.setText(sb.toString());
    }
}
