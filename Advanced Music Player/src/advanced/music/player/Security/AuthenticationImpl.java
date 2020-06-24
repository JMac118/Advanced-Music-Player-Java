/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanced.music.player.Security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AuthenticationImpl implements Authentication {

    static String username;
    static String passwordHash;
    static byte[] salt;
    
    public AuthenticationImpl(){
        
    }

    @Override
    public boolean authenticate(String inUsername, String inPassword){
        
        boolean confirmed = false;
        String passwordHashed = hash(inPassword);
        
        if (username.equals(inUsername) && passwordHash.equals(passwordHashed)) //TODO
        {
            confirmed = true;
        }
        return confirmed; //TODO
    }
    
    public static String hash(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return generatedPassword;
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    public void setAdmin() {
        username = "admin";

        String passwordToHash = "admin";
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        passwordHash = hash(passwordToHash);

        //System.out.println(username + " " + passwordHash);
    }
}
