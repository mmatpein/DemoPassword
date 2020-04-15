package com.politecnico.algoritmos;

import org.mindrot.jbcrypt.BCrypt;

public class Algoritmos {
    public final static int SHA3_512 = 1;
    public final static int BCRYPT = 2;

    public static int getAlgoritmoByNombre(String algoritmo){
        switch (algoritmo.toUpperCase()){
            case "SHA3-512": return SHA3_512;
            case "BCRYPT": return BCRYPT;
            default: return BCRYPT;
        }
    }
}
