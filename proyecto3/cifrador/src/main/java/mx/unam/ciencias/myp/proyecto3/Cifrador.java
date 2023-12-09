package mx.unam.ciencias.myp.proyecto3;

import java.math.BigInteger;
import java.util.Base64;

public class Cifrador {

    public static void main(String[] args) throws Exception {

        if (args[0].equals("c")) {
            CifradorAES cifrador = new CifradorAES(args[1], args[2], args[3]);
            cifrador.cifra();
            BigInteger K = cifrador.obtenSecreto();
            System.out.println(
                    "Para la contraseña: " + args[2] + "su representación numérica es: " + K);
            System.out.println(
                    "El número K de vuelto a contraseña es: " + Base64.getEncoder().encodeToString(K.toByteArray()));

        } else if (args[0].equals("d")) {
            DecifradorAES decifrador = new DecifradorAES(args[1], args[2]);
            decifrador.decifrar();
        }

    }
}
