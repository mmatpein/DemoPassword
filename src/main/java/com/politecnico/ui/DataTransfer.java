package com.politecnico.ui;

public class DataTransfer {
    public String nombre;
    public String hash;
    public String password;
    public String algoritmo;
    public boolean validado;

    @Override
    public String toString() {
        return "DataTransfer{" +
                "nombre='" + nombre + '\'' +
                ", hash='" + hash + '\'' +
                ", password='" + password + '\'' +
                ", algoritmo='" + algoritmo + '\'' +
                '}';
    }
}
