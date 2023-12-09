package mx.unam.ciencias.myd.proyecto3;

import mx.unam.ciencias.myd.proyecto3.ParOrdenado;
import mx.unam.ciencias.myd.proyecto3.Polinomio;

/**
 * Proyecto 3: Secreto compartido Shamir.
 */
public class Proyecto3 {

    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;

    /* Código de terminación por formato invalido. */
    private static final int ERROR_FORMATO = -1;

    /* Código de terminación por archivo invalido. */
    private static final int ERROR_ARCHIVO = -2;

    /* Imprime en pantalla el formato que usa el programa y lo termina.*/
    private static void formato(){
        System.err.printf("El formato de los archivos fragmento es:\n");
    }

    /* Instanciamos una AplicacionShamir y la ejecutamos.*/
    public static void main(String[] args) {
        ParOrdenado<Integer>[] puntos = new ParOrdenado[4];

        puntos[0] = new ParOrdenado(-1,24);
        puntos[1] = new ParOrdenado(1,-4);
        puntos[2] = new ParOrdenado(2,-27);
        puntos[3] = new ParOrdenado(3,-88);
        System.out.println(Polinomio.interpolacion(puntos));
    }
}