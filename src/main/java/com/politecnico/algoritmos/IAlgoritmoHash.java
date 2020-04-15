package com.politecnico.algoritmos;

public interface IAlgoritmoHash {
    String getHash(String password);
    boolean validar(String password, String salt, String hash);
    public String getSalt();
    public void generarSalt();
}
