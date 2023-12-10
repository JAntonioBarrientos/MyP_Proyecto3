package mx.unam.ciencias.myp.proyecto3.test;

import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.Assert;

import mx.unam.ciencias.myp.proyecto3.*;

public class TestCifradorAES {

    public Random random;

    public String rutaDocumentoPrueba = "rutaDocumentoPrueba";
    public String contra = "1234";
    public String rutaDocumentoCifrado = "rutaDocumentoCifrado";

    public TestCifradorAES() {
        random = new Random();
    }

    /**
     * Prueba unitaria para {@link CifradorAES#CifradorAES(String, String, String)}
     */
    public void testConstructor() {

        try {
            new CifradorAES(rutaDocumentoPrueba, contra, rutaDocumentoCifrado);
            Assert.fail();
        } catch (ExcepcionArchivoInvalido e) {
        }

        try {
            File archivo = new File(rutaDocumentoPrueba);
            archivo.createNewFile();
            new CifradorAES(rutaDocumentoCifrado, contra, rutaDocumentoCifrado);
            Assert.fail();
        } catch (ExcepcionArchivoInvalido e) {
        } catch (IOException e) {
        }

        try {
            File archivo = new File(rutaDocumentoPrueba);
            FileOutputStream salida = new FileOutputStream(archivo);
            String cadena = CadenasAleatorias.cadenaAleatoria(random.nextInt(1000) + 1);
            salida.write(cadena.getBytes());
            salida.close();
            archivo.delete();
        } catch (ExcepcionArchivoInvalido e) {
            Assert.fail();
        } catch (IOException e) {
        }

    }

    /**
     * Prueba unitaria para {@link CifradorAES#cifra()}
     */
    @Test
    public void testCifrar() {
        String[] contras1 = { "1234", "contra", "otraContra", "321", "contra1", "CONTRA" };
        String[] contras2 = { "1234", "contra", "otraContra2", "567", "contra2", "CONTRA2" };

        for (int i = 0; i < contras1.length; i++) {
            String infoIgual = CadenasAleatorias.cadenaAleatoria(random.nextInt(1000) + 1);
            auxTestCifrar(contras1[i], contras2[i], infoIgual, infoIgual, true);
        }

        for (int i = 0; i < contras1.length; i++) {
            String info1 = CadenasAleatorias.cadenaAleatoria(random.nextInt(1000) + 1);
            String info2 = CadenasAleatorias.cadenaAleatoria(random.nextInt(1000) + 1);
            auxTestCifrar(contras1[i], contras2[i], info1, info2, false);
        }

    }

    /**
     * Metodo auxiliar para verificar los distintos casos en los que se cifra
     * información
     * 
     * @param contra1   la contraseña para cifrar el primer archivo
     * @param contra2   la contrasñea para cifrar el segundo archivo
     * @param info1     la primer informacion a cifrar
     * @param info2     la segunda informacion a cifrar
     * @param infoIgual si la información recibida para cifrar es la misma o no
     */
    private void auxTestCifrar(String contra1, String contra2, String info1, String info2, boolean infoIgual) {
        try {
            File archivo = new File(rutaDocumentoPrueba);
            FileOutputStream salida = new FileOutputStream(archivo);

            salida.write(info1.getBytes());
            salida.close();

            CifradorAES cifrador1 = new CifradorAES(rutaDocumentoPrueba, contra1, rutaDocumentoCifrado);
            cifrador1.cifra();

            String rutaDocumentoPrueba2 = "rutaDocumentoPrueba2";
            String rutaDocumentoCifrado2 = "rutaDocumentoCifrado2";
            File archivo2 = new File(rutaDocumentoPrueba2);
            FileOutputStream salida2 = new FileOutputStream(archivo2);
            salida2.write(info2.getBytes());
            salida2.close();

            CifradorAES cifrador2 = new CifradorAES(rutaDocumentoPrueba2, contra2, rutaDocumentoCifrado2);
            cifrador2.cifra();

            byte[] bytesArchivoCifrado = Files.readAllBytes(archivo.toPath());
            byte[] bytesArchivoCifrado2 = Files.readAllBytes(archivo2.toPath());

            String datosDecifradosArchivo = Base64.getEncoder().encodeToString(bytesArchivoCifrado);
            String datosDecifradosArchivo2 = Base64.getEncoder().encodeToString(bytesArchivoCifrado2);

            Files.delete(archivo.toPath());
            Files.delete(archivo2.toPath());

            File documentoPruebaCifrado1 = new File(rutaDocumentoCifrado + ".aes");
            Files.delete(documentoPruebaCifrado1.toPath());

            File documentoPruebaCifrado2 = new File(rutaDocumentoCifrado2 + ".aes");
            Files.delete(documentoPruebaCifrado2.toPath());

            if (infoIgual) {
                Assert.assertTrue(Arrays.equals(bytesArchivoCifrado, bytesArchivoCifrado2));
                Assert.assertTrue(datosDecifradosArchivo.equals(datosDecifradosArchivo2));
            } else {
                Assert.assertFalse(Arrays.equals(bytesArchivoCifrado, bytesArchivoCifrado2));
                Assert.assertFalse(datosDecifradosArchivo.equals(datosDecifradosArchivo2));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
