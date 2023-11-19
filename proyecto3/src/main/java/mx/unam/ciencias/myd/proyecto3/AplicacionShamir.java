package mx.unam.ciencias.myd.proyecto3;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.text.NumberFormat;



/**
 * <p>Clase para aplicaciones de archivos mze.</p>
 *
 * <p>La clase cuenta con dos modos, dependiendo de los argumentos
 * ejecuta el modo para resolver laberintos leidos de la entrada 
 * estandar o el modo para generar laberintos dados los parametros
 * escritos como argumentos de la aplicación.</p>
 */

public class AplicacionShamir {

    /* Modo de la aplicación. */
    private enum Modo{
        /* Modo para cifrar texto. */
        CIFRA,
        /* Modo para descifrar texto. */
        DESCIFRA;
    }

    /** El modo de la aplicación. */
    private Modo modo;
    /** El cifrador. */
    private CifradorShamir cifrador;
    /** El descifrador. */
    private DescifradorShamir descifrador;
    /** Llave de cifrado de AES obtenida del hash 
     * de la contraseña con SHA-256.*/
    private long key;

    private ListArray<ParOrdenado> evaluaciones;


    /**
     * Construye una aplicacionShamir con base en los argumentos recibidos.
     * Si se recibe la opción c, se configura al modo cifra, si se recibe la opción d, se configura al modo descifra.
     * @param args arreglo de dimensiones variables recibido del metodo main.
     * @throws IllegalArgumentException si no se especifica un modo de ejecución
    */
    public AplicacionShamir(String ... args){
        if(args.length < 1)
            throw new IllegalArgumentException("Se debe especificar un modo"
            + " de ejecución.\n");
        if(!args[0].toLowerCase().equals("-c") && !args[0].toLowerCase().equals("-d"))
            throw new IllegalArgumentException("Modo de ejecución no válido.\n");
        if(args[0].toLowerCase().equals("-c"))
            this.cifrador = construyeCifrador(args);
        if(args[0].toLowerCase().equals("-d"))
            this.descifrador = construyeDescifrador(args);
    }

    /**
     * Metodo auxiliar que construye una configuración 
     * correcta para el cifrador apartir de un arreglo de argumentos.
     * Se debe proporcionar, en la línea de llamada:
     * 1. La opción c.
     * 2. El nombre del archivo en el que serán guardadas 
     * las n evaluaciones del polinomio.
     * 3. El número total de evaluaciones requeridas (n > 2).
     * 4. El número mínimo de puntos necesarios para descifrar (1 < t ≤ n).
     * 5. El nombre del archivo con el documento claro.
     * @param args arreglo de dimensiones variables recibido del metodo main.
     * @throws ExcepcionOpcionInvalida si los argumentos estan en un formato incorrecto.
     * @return el cifrador construido.
     */
    private CifradorShamir construyeCifrador(String ... args){
        // Codigo pendiente
    }

    /**
     * Metodo auxiliar que construye una configuración 
     * correcta para el descifrador apartir de un arreglo de argumentos.
     * Se debe proporcionar, en la línea de llamada:
     * 1. La opción d.
     * 2. El nombre del archivo con, al menos, t de las n evaluaciones del polinomio.
     * 3. El nombre del archivo cifrado.
     * @param args arreglo de dimensiones variables recibido del metodo main.
     * @throws ExcepcionOpcionInvalida si los argumentos estan en un formato incorrecto.
     * @return el descifrador construido.
     */
    private DescifradorShamir construyeDescifrador(String ... args){
        // Codigo pendiente
    }



    /**
    * Ejecuta el modo de la aplicación.
    */
    public void ejecuta(){
        switch (modo) {
            case CIFRA:
                cifra();
                break;
            case DESCIFRA:
                descifra();
                break;
        }
    }

    /** Modo cifra de la aplicación. .*/
    private void cifra() {
        cifrador.setPassword();
        cifrador.cifra();
    }

    /** Modo descifra de la aplicación. */
    private void descifra() {
        descifrador.descifra();
    }
}