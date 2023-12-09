package mx.unam.ciencias.myd.proyecto3;

import mx.unam.ciencias.myd.proyecto3.ParOrdenado;

/**
 * <p> Clase para representar polinomios.</p>
 * 
 * <p> La clase cuenta con métodos para realizar operaciones de polinomios.</p>
 
 */

public class Polinomio{

    /** El grado del polinomio. */
    int grado;
    /** Los coeficientes del polinomio. El indice i se 
     * encuentra el coeficiente termino con grado i.
    */
    int[] coeficientes;

    /** 
     * Constructor que recibe un arreglo de coeficientes.
     * @param coeficientes el arreglo de coeficientes.
     */
    public Polinomio(int[] coeficientes){
        this.coeficientes = coeficientes;
    }

    /** 
     * Constructor que recibe el grado del polinomio.
     * @param grado el grado del polinomio.
     */
    public Polinomio(int grado){
        coeficientes = new int[grado +1];
    }

    /** 
     * Metodo que asigna un coeficiente a un grado dado.
     * @param coeficiente el coeficiente del polinomio.
     * @param grado el grado del polinomio.
     * @throws IllegalArgumentException si el grado es mayor al grado del polinomio.
     */
    public void setCoeficiente(int coeficiente, int grado){
        coeficientes[grado] = coeficiente;
    }

    /** 
     * Metodo que regresa el coeficiente de un grado dado.
     * @param grado el grado del polinomio.
     * @return el coeficiente del polinomio.
     */
    public long getCoeficiente(long grado){
        return coeficientes[grado];
    }

    /** 
     * Metodo que regresa el grado del polinomio.
     * @return el grado del polinomio.
     */
    public int getGrado(){
        return grado;
    }


    /**
     * Método que evalúa el polinomio en un valor dado.
     * @param x el valor en el que se evaluará el polinomio.
     * @return el valor del polinomio evaluado en x.
     */
    public long evalua(long x){
        long factor = 1;
        long resultado = 0;
        for(int i =0 ; i < grado; i++){
            resultado += coeficientes[i] * factor;
            factor *= x; 
        }
        return resultado;
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