package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de {@link Docuemnto}
 */
public class TestDocumento {

    private static final String rutaDocumentoTest = "testClaseDocumento";
    private Random random;
    private int longitudCadenas = 1000;

    /**
     * Constructor
     */
    public TestDocumento() {
        random = new Random();
    }

    /**
     * Prueba unitaria para {@link Documento#Documento}
     */
    @Test
    public void testConstructorDocumento() {

        try {
            File archivo = new File(rutaDocumentoTest);
            FileOutputStream salida = new FileOutputStream(archivo);

            String cadena = CadenasAleatorias.cadenaAleatoria(random.nextInt(longitudCadenas) + 1);
            salida.write(cadena.getBytes());
            new Documento(rutaDocumentoTest);
            salida.close();
            archivo.delete();
        } catch (IOException e) {
        } catch (ExcepcionArchivoInvalido e) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Documento#obtenBytesDocumento}.
     */
    @Test
    public void testObtenBytesDocumento() {
        try {
            File archivo = new File(rutaDocumentoTest);
            FileOutputStream salida = new FileOutputStream(archivo);
            String cadenaEsperada;
            byte[] datosEsperados;
            Documento documento;

            cadenaEsperada = CadenasAleatorias.cadenaAleatoria(random.nextInt(longitudCadenas) + 1);
            datosEsperados = cadenaEsperada.getBytes();
            salida.write(datosEsperados);
            documento = new Documento(rutaDocumentoTest);
            Assert.assertArrayEquals(datosEsperados, documento.leeBytesDocumento());

            salida.close();
            Files.delete(archivo.toPath());
        } catch (IOException e) {
        }
    }

    /**
     * Prueba unitaria para {@link Documento#escribeBytesDocumento(byte[])}
     */
    @Test
    public void testEscribeBytesDocumento() {

        try {
            File archivo = new File(rutaDocumentoTest);
            FileOutputStream salida = new FileOutputStream(archivo);
            salida.write("cadena".getBytes());

            Documento documento = new Documento(rutaDocumentoTest);
            String cadena;
            cadena = CadenasAleatorias.cadenaAleatoria(random.nextInt(longitudCadenas) + 1);
            byte[] datosEsperados;
            datosEsperados = cadena.getBytes();

            documento.escribeBytesDocumento(datosEsperados);

            FileInputStream entrada = new FileInputStream(archivo);
            byte[] datosObtenidos = new byte[datosEsperados.length];
            entrada.read(datosObtenidos);

            Assert.assertArrayEquals(datosEsperados, datosObtenidos);
            entrada.close();
            salida.close();

            Files.delete(archivo.toPath());
        } catch (IOException e) {
        }

    }

}
