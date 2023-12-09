package mx.unam.ciencias.myd.proyecto3;

import mx.unam.ciencias.myd.proyecto3.ParOrdenado;

/**
 * <p> Clase para representar polinomios.</p>
 * 
 * <p> La clase cuenta con métodos para realizar operaciones de polinomios.</p>
 
 */

public class Polinomio{

    /** El grado del polinomio. */
    long grado;
    /** Los coeficientes del polinomio. El indice i se 
     * encuentra el coeficiente termino con grado i.
    */
    long[] coeficientes;

    /** 
     * Constructor que recibe un arreglo de coeficientes.
     * @param coeficientes el arreglo de coeficientes.
     */
    public Polinomio(long[] coeficientes){
        // Codigo pendiente
    }

    /** 
     * Constructor que recibe el grado del polinomio.
     * @param grado el grado del polinomio.
     */
    public Polinomio(long grado){
        // Codigo pendiente
    }

    /** 
     * Metodo que asigna un coeficiente a un grado dado.
     * @param coeficiente el coeficiente del polinomio.
     * @param grado el grado del polinomio.
     * @throws IllegalArgumentException si el grado es mayor al grado del polinomio.
     */
    public void setCoeficiente(long coeficiente, long grado){
        // Codigo pendiente
    }

    /** 
     * Metodo que regresa el coeficiente de un grado dado.
     * @param grado el grado del polinomio.
     * @return el coeficiente del polinomio.
     */
    public long getCoeficiente(long grado){
        // Codigo pendiente
        return 0;
    }

    /** 
     * Metodo que regresa los coeficientes del polinomio.
     * @return los coeficientes del polinomio.
     */
    public long getCoeficientes(long[] grado){
        // Codigo pendiente
        return 0;
    }


    /** 
     * Metodo que regresa el grado del polinomio.
     * @return el grado del polinomio.
     */
    public long getGrado(){
        // Codigo pendiente
        return 0;
    }


    /**
     * Método que evalúa el polinomio en un valor dado.
     * @param x el valor en el que se evaluará el polinomio.
     * @return el valor del polinomio evaluado en x.
     */
    public long evalua(long x){
        return x;
    }

    /**
     * 
     * Realiza una version simplificada de la interpolacion
     * de polinomios de lagrange para obtener el termino 
     * independiente de un polinomio dado n puntos.
     */
    public static long interpolacion(ParOrdenado<Integer>[] puntos){
        int n = puntos.length;
        long k=0;
        double num= 1;
        double den = 1;
        for(int i = 0; i < n; i++){
            num = puntos[i].getY();
            for(int j = 0; j < n; j++){
                if(i == j)
                    continue;
                num *= puntos[j].getX();
                den *= puntos[i].getX() - puntos[j].getX();
            }
            k += (int)num/den;
            num = 1;
            den = 1;
        }

        if(n % 2 == 0)
            k = -k;
        return k;
    }
}