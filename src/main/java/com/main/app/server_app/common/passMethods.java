package com.main.app.server_app.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passMethods{
    

    public static String encrypt(String pass){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String newPass = no.toString(16);
            while(newPass.length() < 32){
                newPass = "0" + newPass;
            }
            consoleLogger.info("Password is encrypted!");
            return newPass;
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

}