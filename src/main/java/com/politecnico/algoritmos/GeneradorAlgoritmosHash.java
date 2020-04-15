package com.politecnico.algoritmos;

import java.security.NoSuchAlgorithmException;

public class GeneradorAlgoritmosHash {


    public static IAlgoritmoHash getAlgoritmoHash(int algoritmo) throws NoSuchAlgorithmException {
        switch (algoritmo){
            case Algoritmos.SHA3_512:
                return new HashSHA3_512();
            case Algoritmos.BCRYPT:
                return new HashBcrypt();
        }
        return new HashBcrypt();
    }
}
