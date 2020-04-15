package com.politecnico.dao;

import com.politecnico.modelo.Credencial;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CredencialesDAO {

    DBConn dbConn;

    public CredencialesDAO() throws SQLException {
        dbConn = new DBConn();
    }

    public ArrayList<Credencial> getCredencialesFromDB() throws SQLException{
        ResultSet result = dbConn.select("SELECT nombre, salt, hash, algoritmo FROM Credenciales");
        ArrayList<Credencial> credenciales = new ArrayList<>();
        while (result.next()){
            String nombre = result.getString("nombre");
            String salt = result.getString("salt");
            String hash = result.getString("hash");
            int algoritmo = result.getInt("algoritmo");
            Credencial credencial = new Credencial();
            credencial.setCredencialByHash(nombre,salt,hash,algoritmo);
            credenciales.add(credencial);
        }
        return credenciales;
    }

    public int insertarCredencial(Credencial credencial) throws SQLException {
        return dbConn.update("INSERT INTO Credenciales (nombre, hash, algoritmo, salt) VALUES ('"+credencial.getNombre()+"','"+credencial.getHash()+"','"+credencial.getAlgoritmo()+"','"+credencial.getSalt()+"')");
    }



}
