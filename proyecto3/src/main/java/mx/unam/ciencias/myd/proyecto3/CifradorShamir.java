package mx.unam.ciencias.myd.proyecto3;

import java.security.SecureRandom;
import java.io.File;

public class CifradorShamir {

    /** El nombre del archivo en el que serán guardadas 
     * las n evaluaciones del polinomio. */
    private String documentoEvaluaciones;
    /** El número total de evaluaciones requeridas (n > 2). */
    private int n;
    /** El número mínimo de puntos necesarios para descifrar (1 < t ≤ n). */
    private int t;
    /** El nombre del archivo con el documento claro. */
    private String rutaArchivoClaro;
    /** Contraseña para cifrar el documento claro.*/
    private String password;
    /** Llave de cifrado de AES obtenida del hash 
     * de la contraseña con SHA-256.*/
    private long key;
    /** El documento cifrado. */
    private File documentoCifrado;
    /** Polinomio de interpolacion. */
    private Polinomio polinomio;
    /** RNG. */
    protected SecureRandom random;
    /** Extension del archivo cifrado. */
    private final String extensionCifrado = ".aes";
    /** Extension del archivo de evaluaciones del polinomio */
    private final String extensionEvaluaciones = ".frg";

    /** Constructor que recibe los argumentos para construir un cifrador.
     * @param rutaArchivoEvaluaciones el nombre del archivo en el que serán guardadas
     * @param n el número total de evaluaciones requeridas (n > 2).
     * @param t el número mínimo de puntos necesarios para descifrar (1 < t ≤ n).
     * @param rutaArchivoClaro el nombre del archivo con el documento claro.
     * @throws IllegalArgumentException si n < 2 o t < 1 o t > n.
     * @throws ExcepcionArchivoInvalido si el archivo no existe.
     * 
     */
    public CifradorShamir(String documentoEvaluaciones, 
                            int n, int t, String rutaArchivoClaro){
        // Codigo pendiente
    }

    /** 
     * Se solicita al usuario una contraseña (sin hacer
     *  eco de la misma en la terminal) y se guarda en el atributo
     *  password. Ademas se genera una llave de cifrado de 256 bits 
     * y se guarda en el atributo key.
     * @throws ExcepcionLectura si ocurre un error al leer la contraseña.
    */
    public void setPassword(){
        // Codigo pendiente
    }

    /**
     * Usar Key como la llave de cifrado de AES para cifrar el documento claro.
     */
    public void cifra(){
        // Codigo pendiente
        cifraDocumento();
        this.polinomio = generaPolinomioAleatorio(n);
        guardaEvaluaciones();
    }

    /** 
     * Cifra el documento claro con la llave de cifrado Key y lo guarda.
     */
    private void cifraDocumento(){
        // Codigo pendiente
    }

    /**
     * Genera un polinomio aleatorio de grado n y establece 
     * a su termino independiente como key
     * @param grado el grado del polinomio.
     * @return el polinomio generado.
     */
    private Polinomio generaPolinomioAleatorio(int grado){
        // Codigo pendiente
        return null;
    }

    /**
     * Guarda las evaluaciones del polinomio en un archivo.
     */
    private void guardaEvaluaciones(){
        // Codigo pendiente
    }
    
}