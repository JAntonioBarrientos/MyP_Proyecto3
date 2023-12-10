package mx.unam.ciencias.myp.proyecto3;

import java.util.Random;

public class CadenasAleatorias {

    /**
     * Metodo auxiliar para generar cadenas aleatorias
     * 
     * @param longitud longiutd de la cadena deseada
     * @return una cadena aletorio con caracteres en el alfabeto tradicional
     */
    public static String cadenaAleatoria(int longitud) {

        String alfabeto = "ABCDEFGHIJKLMÑOPQRSTUVWXYZabcdefghijklmnñopqrstvwxyz";
        int longitudAlfabeto = alfabeto.length();
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 1; i < longitud; i++) {
            char c = alfabeto.charAt(random.nextInt(longitudAlfabeto));
            sb.append(c);
        }

        return sb.toString();
    }

}
