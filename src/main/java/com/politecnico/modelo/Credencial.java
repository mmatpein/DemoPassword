package com.politecnico.modelo;

import com.politecnico.algoritmos.GeneradorAlgoritmosHash;
import com.politecnico.algoritmos.IAlgoritmoHash;

import java.security.NoSuchAlgorithmException;

public class Credencial {
    private String nombre;
    private String salt;
    private String hash;
    private int algoritmo;


    public void setCredencialByPassword(String nombre, String password, int algoritmo) throws NoSuchAlgorithmException {
        this.nombre = nombre;
        this.algoritmo = algoritmo;
        IAlgoritmoHash algoritmoHash = GeneradorAlgoritmosHash.getAlgoritmoHash(algoritmo);
        algoritmoHash.generarSalt();
        salt = algoritmoHash.getSalt();
        hash = algoritmoHash.getHash(password);
        System.out.println("setCredencialByPassword"+toString());
    }

    public void setCredencialByHash(String nombre, String salt, String hash, int algoritmo){
        this.nombre = nombre;
        this.salt = salt;
        this.hash = hash;
        this.algoritmo = algoritmo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public int getAlgoritmo() {
        return algoritmo;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "nombre='" + nombre + '\'' +
                ", salt='" + salt + '\'' +
                ", hash='" + hash + '\'' +
                ", algoritmo=" + algoritmo +
                '}';
    }
}
