package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <p>
 * Clase que permite escribir bytes y leer bytes de documentos
 * </p>
 */
public class Documento {

    /** La ruta del documento */
    private String rutaDocumento;

    /**
     * Constructor que inicializa las propiedades de un documento claro
     * 
     * @param rutaDocumento la dirección del documento
     */
    public Documento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    /**
     * Lee el contendio del archivo y regresa los bytes
     * 
     * @return un arreglo de bytes con el contenido del archivo
     */
    public byte[] leeBytesDocumento() {
        byte[] buffer = null;
        try {
            File archivo = new File(rutaDocumento);
            buffer = Files.readAllBytes(archivo.toPath());
            return buffer;
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer del archivo " + rutaDocumento);
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Escribe los datos (bytes) decifrados en el mismo documento cifrado.
     * Es una operacion destructiva
     * 
     * @param bytes los bytes a escribir en el documento
     */
    public void escribeBytesDocumento(byte[] bytes) {
        try (FileOutputStream salida = new FileOutputStream(new File(rutaDocumento))) {
            salida.write(bytes);
        } catch (IOException e) {
            System.err.println("Error al escribir en el documento");
            e.printStackTrace();
        }
    }
}
