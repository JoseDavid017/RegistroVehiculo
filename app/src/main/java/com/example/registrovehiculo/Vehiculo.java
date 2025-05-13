package com.example.registrovehiculo;

public class Vehiculo {
    private String placa;
    private String marca;
    private String color;

    public Vehiculo(String placa, String marca, String color) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getColor() { return color; }

    public void setPlaca(String placa) { this.placa = placa; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setColor(String color) { this.color = color; }
}
