package com.politecnico.modelo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Credenciales {
    ArrayList<Credencial> credenciales;

    public Credenciales(){
        credenciales = new ArrayList<>();
    }

    public Credenciales(ArrayList<Credencial> credenciales){
        this.credenciales = credenciales;
    }

    public void addCredencial(Credencial credencial) throws NoSuchAlgorithmException {
        credenciales.add(credencial);
    }

    public Credencial getCredencialByNombre(String nombre) {
        for (Credencial credencial : credenciales) {
            if (credencial.getNombre().equals(nombre)) {
                return credencial;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Credenciales{" +
                "credenciales=" + credenciales +
                '}';
    }

    public ArrayList<String> getNombresUsuario() {
        ArrayList<String> nombresUsuario = new ArrayList<>();
        for (Credencial credencial : credenciales)
            nombresUsuario.add(credencial.getNombre());
        return nombresUsuario;
    }
}
