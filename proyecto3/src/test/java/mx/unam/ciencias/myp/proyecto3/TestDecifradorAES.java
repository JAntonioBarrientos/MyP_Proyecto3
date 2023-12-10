package mx.unam.ciencias.myp.proyecto3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para las pruebas unitarias de {@link DecifradorAES}
 */
public class TestDecifradorAES {

    @Test
    public void testDecifrar() {

        String[] contrasCifrado = { "contra", "123", "321", "CONTRA", "", "@@pfñq", "**¨{{a}" };
        String[] contrasDecifrado = { "contrA", "124", "32", "COTRA", "ladsjfjaf", "30'¿3", "" };

        for (int i = 0; i < contrasCifrado.length; i++)
            auxTestDecifrar(contrasCifrado[i], contrasCifrado[i], true);

        for (int i = 0; i < contrasCifrado.length; i++)
            auxTestDecifrar(contrasCifrado[i], contrasDecifrado[i], false);

    }

    /**
     * Metodo auxiliar para comprobar si decifrar la información con la misma
     * contraseña o con distinta contraseña
     * la misma contraseña funciona como debería
     * 
     * @param contraCifrado   la contraseña para cifrar
     * @param contraDecifrado la contraseña para decifrar
     */
    private void auxTestDecifrar(String contraCifrado, String contraDecifrado, boolean mismaContra) {
        try {
            String rutaArchivoTemporal = "archivoTemporal";
            File archivoTemporal = new File(rutaArchivoTemporal);
            FileOutputStream salidaArchivoTemporal = new FileOutputStream(archivoTemporal);
            Random random = new Random();
            String cadenaEsperada = CadenasAleatorias.cadenaAleatoria(random.nextInt(10000) + 1);

            byte[] bytesCadenaEsperada = cadenaEsperada.getBytes();
            salidaArchivoTemporal.write(bytesCadenaEsperada);

            String contra = "1234";
            CifradorAES cifrador = new CifradorAES(rutaArchivoTemporal, contra, rutaArchivoTemporal);
            cifrador.cifra();

            String rutaArchivoCifrado = rutaArchivoTemporal + ".aes";
            DecifradorAES decifrador = new DecifradorAES(rutaArchivoCifrado, contra);
            decifrador.decifrar();

            FileReader fd = new FileReader(rutaArchivoTemporal);
            BufferedReader entrada = new BufferedReader(fd);
            String cadena;
            String cadenaRecibida = "";

            while ((cadena = entrada.readLine()) != null) {
                cadenaRecibida += cadena;
            }

            Files.delete(archivoTemporal.toPath());

            salidaArchivoTemporal.close();
            entrada.close();

            Assert.assertTrue(cadenaEsperada.equals(cadenaRecibida));

        } catch (InvalidKeyException e) {
            if (!mismaContra)
                Assert.assertTrue(true);
            else
                Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
