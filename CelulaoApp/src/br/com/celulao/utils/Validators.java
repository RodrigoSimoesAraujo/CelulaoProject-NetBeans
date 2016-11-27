package br.com.celulao.utils;

/**
 * Created by SYSTEM on 19/11/2016.
 */
public class Validators {
    public static boolean isCPFValid(String CPF){
        if(CPF.length()<5 || CPF.isEmpty()) return false;
        else return true;
    }

    public static boolean isCNPJValid(String CNPJ){
        if(CNPJ.length()<5 || CNPJ.isEmpty()) return false;
        else return true;
    }
}
