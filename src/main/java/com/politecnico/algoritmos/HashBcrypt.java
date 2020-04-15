package com.politecnico.algoritmos;

import org.mindrot.jbcrypt.BCrypt;

public class HashBcrypt implements IAlgoritmoHash {

    private String salt = "";

    @Override
    public String getHash(String password) {
        return BCrypt.hashpw(password,salt);
    }

    @Override
    public boolean validar(String password, String salt, String hash) {
        return BCrypt.checkpw(password,hash);
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void generarSalt() {
        salt = BCrypt.gensalt();
    }
}
