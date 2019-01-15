package com.santander.test.backend.bweninger.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    public static String criptografarSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md5.digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    public static Boolean validarSenha(String senha1, String senha2) {
        try {
            return criptografarSenha(senha1).equals(criptografarSenha(senha2));
        } catch (UnsupportedEncodingException e) {
            return false;
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
