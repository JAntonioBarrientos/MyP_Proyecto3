package mx.unam.ciencias.myp.proyecto3;

import java.math.BigInteger;
import java.util.Base64;

/**
 * Clase principal para correr el programa
 */
public class Cifrador {

    /**
     * Metodo principal para correr el programa
     * 
     * @param args los argumentos de la linea de comandos
     * @throws Exception en caso de error
     */
    public static void main(String[] args) throws Exception {

        if (args[0].equals("c")) {
            CifradorAES cifrador = new CifradorAES(args[1], args[2], args[3]);
            cifrador.cifra();
            BigInteger K = cifrador.obtenSecreto();
            System.out.println(
                    "Para la contraseña: " + args[2] + " su representación numérica es: " + K);
            String contra1 = new String(K.toByteArray());
            byte[] bytesContra = DispersadorSHA256.dispersa(args[2]);
            System.out.println("La contra dispersada es: " + Base64.getEncoder().encodeToString(bytesContra));
            System.out.println(
                    "El número K de vuelto a la dispersion de la contraseña es: "
                            + Base64.getEncoder().encodeToString(K.toByteArray()));
            System.out.println(
                    "El número K de vuelto a contraseña es: " + contra1);

        } else if (args[0].equals("d")) {
            DecifradorAES decifrador = new DecifradorAES(args[1], args[2]);
            decifrador.decifrar();
        }

    }
}
