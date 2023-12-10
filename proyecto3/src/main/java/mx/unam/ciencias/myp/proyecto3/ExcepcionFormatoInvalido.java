package mx.unam.ciencias.myp.proyecto3;

/**
 * Clase para excepciones por formato invalido
 */
public class ExcepcionFormatoInvalido extends IllegalArgumentException {

    /**
     * Constructor vacío.
     */
    public void ExcepcionFormatoInvalido() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionFormatoInvalido(String mensaje) {
        super(mensaje);
    }
}