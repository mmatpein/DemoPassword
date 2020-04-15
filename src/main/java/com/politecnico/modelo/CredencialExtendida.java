package com.politecnico.modelo;

public class CredencialExtendida extends Credencial {
    private String password;
    private boolean validada;

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidada() {
        return validada;
    }

    public void setValidada(boolean validada) {
        this.validada = validada;
    }
}
