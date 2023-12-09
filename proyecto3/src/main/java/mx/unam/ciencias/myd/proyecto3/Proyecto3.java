package mx.unam.ciencias.myd.proyecto3;

import mx.unam.ciencias.myd.proyecto3.ParOrdenado;
import mx.unam.ciencias.myd.proyecto3.Polinomio;
import java.math.BigInteger;


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
        ParOrdenado<Integer>[] puntos = new ParOrdenado[6];

        puntos[0] = new ParOrdenado(-3,-1968);
        puntos[1] = new ParOrdenado(-2,-360);
        puntos[2] = new ParOrdenado(-1,-52);
        puntos[3] = new ParOrdenado(1,-24);
        puntos[4] = new ParOrdenado(2,92);
        puntos[5] = new ParOrdenado(3,900);
        System.out.println(Polinomio.interpolacion(puntos));
        //Vamos a llenar con los mismos valores de arriba pero con BigInteger
        ParOrdenado<BigInteger>[] puntosBig = new ParOrdenado[6];

        puntosBig[0] = new ParOrdenado(new BigInteger("-3"),new BigInteger("-1968"));
        puntosBig[1] = new ParOrdenado(new BigInteger("-2"),new BigInteger("-360"));
        puntosBig[2] = new ParOrdenado(new BigInteger("-1"),new BigInteger("-52"));
        puntosBig[3] = new ParOrdenado(new BigInteger("1"),new BigInteger("-24"));
        puntosBig[4] = new ParOrdenado(new BigInteger("2"),new BigInteger("92"));
        puntosBig[5] = new ParOrdenado(new BigInteger("3"),new BigInteger("900"));
        System.out.println(Polinomio.interpolacionBig(puntosBig));
    }
}