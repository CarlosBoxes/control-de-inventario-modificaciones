/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ch470
 */
public class Seguridad {
   
/**
 *
 */


    //algoritmos

    public static String MD2 = "MD2";

    public static String MD5 = "MD5";

    public static String SHA1 = "SHA-1";

    public static String SHA256 = "SHA-256";

    public static String SHA384 = "SHA-384";

    public static String SHA512 = "SHA-512";

 

    /***

     * Convierte un arreglo de bytes a String usando valores hexadecimales

     * @param digest arreglo de bytes a convertir

     * @return String creado a partir de <code>digest</code>

     */

    private static String toHexadecimal(byte[] digest){

        String hash = "";

        for(byte aux : digest) {

            int b = aux & 0xff;

            if (Integer.toHexString(b).length() == 1) hash += "0";

            hash += Integer.toHexString(b);

        }

        return hash;

    }

 

    /***

     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.

     * @param message texto a encriptar

     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512

     * @return mensaje encriptado

     */

    public  String getStringMessageDigest(String password){

        byte[] digest = null;
        byte[] buffer = password.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);

    }


    
}
