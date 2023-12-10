package mx.unam.ciencias.myp.proyecto3.test;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.junit.Assert;

import mx.unam.ciencias.myp.proyecto3.*;

public class TestDispersadorSHA256 {

    /**
     * Prueba unitaria para {@link DispersadorSHA256#dispersa(String)}
     */
    @Test
    public void testDispersa() {

        Random random = new Random();

        String cadena1;
        String cadena2;

        for (int i = 0; i < 20; i++) {
            cadena1 = CadenasAleatorias.cadenaAleatoria(random.nextInt(255));
            cadena2 = CadenasAleatorias.cadenaAleatoria(random.nextInt(255)) + " ";
            auxTestDispersa(cadena1, cadena2, false);
        }

        for (int i = 0; i < 20; i++) {
            cadena1 = CadenasAleatorias.cadenaAleatoria(random.nextInt(255));
            auxTestDispersa(cadena1, cadena1, true);
        }

    }

    /**
     * Metodo auxiliar para verificar si la msima cadena es dispersada igual o si
     * cadenas distintas son dispersadas distintas (esto podría fallar pero con
     * muy baja probabilidad)
     * 
     * @param cadena1     la primera cadena a ser dispersada
     * @param cadena2     la segunda cadena a ser dispersada
     * @param mismaCadena si las cadenas son la misma o no
     */
    private void auxTestDispersa(String cadena1, String cadena2, boolean mismaCadena) {

        byte[] cadenaDispersada1 = DispersadorSHA256.dispersa(cadena1);
        byte[] cadenaDispersada2 = DispersadorSHA256.dispersa(cadena2);

        if (mismaCadena)
            Assert.assertTrue(Arrays.equals(cadenaDispersada1, cadenaDispersada2));
        else {
            Assert.assertFalse(Arrays.equals(cadenaDispersada1, cadenaDispersada2));
        }
    }

}
