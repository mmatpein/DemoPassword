package com.politecnico.algoritmos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class HashSHA3_512 implements IAlgoritmoHash{
    MessageDigest digest;
    String salt;

    public HashSHA3_512() throws NoSuchAlgorithmException {
        salt = "";
        digest = MessageDigest.getInstance("SHA3-512");
    }

    @Override
    public String getHash(String password) {
        digest.update(salt.getBytes());
        byte[] bytesHash = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < bytesHash.length; i++){
            sb.append(Integer.toString((bytesHash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    @Override
    public boolean validar(String password, String salt, String hash) {
        this.salt = salt;
        String nuevoHash = getHash(password);
        return nuevoHash.equals(hash);
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void generarSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] bytesSalt = new byte[16];
            sr.nextBytes(bytesSalt);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytesSalt.length ;i++)
            {
                sb.append(Integer.toString((bytesSalt[i] & 0xff) + 0x100, 16).substring(1));
            }
            salt = sb.toString();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e){
            e.printStackTrace();
            salt = "0cebad563b238712";
        }
    }
}
