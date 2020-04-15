package com.politecnico.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
    private final static int TIMEOUT_TEST_CONEXION_SEGUNDOS = 3;
    Connection dbConn;
    public DBConn() throws SQLException {
        Dotenv dotenv = Dotenv.configure().load();
        String db = dotenv.get("DB_NAME");
        String host = "jdbc:mysql://"+dotenv.get("DB_HOST") + ":3306/"+db;
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        System.out.println(host+";"+user+";"+password);
        dbConn = DriverManager.getConnection(host,user,password);
    }

    public ResultSet select(String consulta) throws SQLException {
        comprobarConexion();
        return dbConn.prepareStatement(consulta).executeQuery();
    }

    public int update(String consulta) throws SQLException {
        comprobarConexion();
        return dbConn.prepareStatement(consulta).executeUpdate();
    }

    public void comprobarConexion() throws SQLException {
        if (dbConn == null)
            throw new SQLException("No hay conexión a la base de datos");
        /*else if (dbConn.isValid(TIMEOUT_TEST_CONEXION_SEGUNDOS))
            throw new SQLException("La conexión ha dejado de ser válida");*/
    }
}
