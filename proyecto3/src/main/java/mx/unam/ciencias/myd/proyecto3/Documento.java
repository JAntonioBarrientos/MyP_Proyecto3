package mx.unam.ciencias.myd.proyecto3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Documento {

    /** Los bytes del documento */
    private byte[] bytesDocumento;

    /** La ruta del documento */
    private String rutaDocumento;

    /**
     * Constructor que inicializa las propiedades de un documento claro
     * o cualquiera
     * 
     * @param rutaDocumento la dirección del documento
     */
    public Documento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
        bytesDocumento = inicializaBytes();
    }

    /**
     * Lee los bytes del documento y los guarda en un arreglo
     * 
     * @return los bytes del documento
     */
    private byte[] inicializaBytes() {
        byte[] buffer = null;
        try (FileInputStream entrada = new FileInputStream(new File(rutaDocumento))) {
            buffer = new byte[entrada.available()];
            entrada.read(buffer);
            entrada.close();
            return buffer;
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer del archivo " + rutaDocumento);
        }
        return buffer;
    }

    /**
     * Escribe los datos (bytes) decifrados en el mismo documento cifrado.
     * Es una operacion destructiva
     * 
     * @param bytesDocumentoDecifrado los bytes decifrados del documento cifrado
     */
    public void escribeBytesDocumento(byte[] bytes) {
        try (FileOutputStream salida = new FileOutputStream(new File(rutaDocumento))) {
            salida.write(bytes);
        } catch (IOException e) {
            System.err.println("Error al escribir en el documento");
        }
    }

    /**
     * Regresa los bytes del documento
     * 
     * @return un arreglo con los bytes del documento
     */
    public byte[] obtenBytesDocumento() {
        return bytesDocumento;
    }
}
