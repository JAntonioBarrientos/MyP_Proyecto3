package mx.unam.ciencias.myp.proyecto3;

/**
 * Clase para excepciones por entrada invalida de archivos.
 */
public class ExcepcionArchivoInvalido extends IllegalArgumentException {

    /**
     * Constructor vacío.
     */
    public ExcepcionArchivoInvalido() {
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * 
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionArchivoInvalido(String mensaje) {
        super(mensaje);
    }
}