package com.politecnico.control;

import com.politecnico.algoritmos.GeneradorAlgoritmosHash;
import com.politecnico.algoritmos.IAlgoritmoHash;
import com.politecnico.dao.CredencialesDAO;
import com.politecnico.modelo.Credencial;
import com.politecnico.modelo.Credenciales;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ControlCredenciales {
    Credenciales credenciales;

    public ControlCredenciales() throws NoSuchAlgorithmException {
    }

    public Credencial addCredencial(String nombre, String password, int algoritmo) throws NoSuchAlgorithmException, SQLException {
        Credencial credencial = new Credencial();
        credencial.setCredencialByPassword(nombre,password,algoritmo);
        CredencialesDAO credencialesDAO = new CredencialesDAO();
        if (credencialesDAO.insertarCredencial(credencial) == 1)
            credenciales.addCredencial(credencial);
        else
            throw new SQLException("No se pudo insertar en la base de datos");
        return credencial;
    }

    public boolean comprobarCredencial(String nombre, String password)  {
        Credencial credencial = credenciales.getCredencialByNombre(nombre);
        boolean validado = false;
        if (credencial != null){
            System.out.println(credencial);
            try {
                IAlgoritmoHash algoritmo = GeneradorAlgoritmosHash.getAlgoritmoHash(credencial.getAlgoritmo());
                validado = algoritmo.validar(password,credencial.getSalt(),credencial.getHash());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                validado = false;
            }
        } else {
            throw new NoSuchElementException("No existe un usuario llamado " + nombre);
        }
        return validado;
    }

    public String getHashByNombre(String nombre){
        Credencial credencial =  credenciales.getCredencialByNombre(nombre);
        if (credencial != null)
            return credencial.getHash();
        else
            return null;
    }

    public ArrayList<String> getNombresUsuario(){
        return credenciales.getNombresUsuario();
    }

    public void cargarCredencialesDesdeBD() throws SQLException {
        CredencialesDAO credencialesDAO = new CredencialesDAO();
        credenciales = new Credenciales(credencialesDAO.getCredencialesFromDB());
    }
}
