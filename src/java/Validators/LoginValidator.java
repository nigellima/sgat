package Validators;


import entities.Pessoa;
import entities.Usuario;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HUUFMA
 */
public class LoginValidator {

    public LoginValidator(){
    }
    
    public static boolean validLogin(Usuario usuario, String passwordField) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        
        //Hashing.sha1().hashString().toString();
        String encryptedPassw = encryptPassword(passwordField);
        if(usuario.getSenha().equals(encryptedPassw))
        {
            return true;
        }
        else
            return false;
    }
    
    private static String encryptPassword(String password) 
            throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));

        return new BigInteger(1, crypt.digest()).toString(16);
    }
}
