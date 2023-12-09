package mx.unam.ciencias.myp.proyecto3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DispersadorSHA256 {

    /** Algoritmo de dispersion para la contrasña */
    private static final String TIPO_DE_DISPERSION = "SHA-256";

    /**
     * Dispersa la cadena ingresada por el usuario con el algortimo SHA-256
     * 
     * @return la dispersion de la cadena en bytes
     */
    public static byte[] dispersa(String cadena) {
        byte[] bytesCadena = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(TIPO_DE_DISPERSION);
            bytesCadena = digest.digest(cadena.getBytes(StandardCharsets.UTF_8));
            return bytesCadena;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("El algoritmo que se trata de usar no existe");
        }
        return bytesCadena;
    }
}
