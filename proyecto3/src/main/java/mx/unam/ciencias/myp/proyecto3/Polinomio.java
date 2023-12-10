package mx.unam.ciencias.myp.proyecto3;

import mx.unam.ciencias.myd.proyecto3.ParOrdenado;

import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;
import java.util.Set;
import java.util.*;



/**
 * <p> Clase para representar polinomios.</p>
 * 
 * <p> La clase cuenta con métodos para realizar operaciones de polinomios con enteros 
 * muy grandes. Todo bajo el modulo con un numero primero de 78 digitos.</p>
 
 */

public class Polinomio{

    /** El modulo con el que se trabajara. Se trata de un numero primo
     * de 78 digitos.
     */
    public static final BigInteger MODP = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");

    /** El grado del polinomio. */
    private int grado;
    /** Los coeficientes del polinomio. El indice i se 
     * encuentra el coeficiente termino con grado i.
    */
    private BigInteger[] coeficientes;

    /** 
     * Constructor que recibe un arreglo de coeficientes. NO NULOS.
     * @param coeficientes el arreglo de coeficientes.
     */
    public Polinomio(BigInteger[] coeficientes){
        if(coeficientes == null)
            throw new IllegalArgumentException("El arreglo de coeficientes no puede ser nulo.");
        this.grado = coeficientes.length;
        this.coeficientes = coeficientes;
    }

    /** 
     * Constructor que recibe el grado del polinomio.
     * @param grado el grado del polinomio.
     */
    public Polinomio(int grado){
        this.grado = grado;
        coeficientes = new BigInteger[grado +1];
        for(int i = 0; i < grado + 1; i++)
            coeficientes[i] = new BigInteger("0");
    }

    /** 
     * Constructor que recibe el termino constante y el grado del polinomio, para
     * despues generar un polinomio de grado n con el termino constante dado y los
     * demas coeficientes aleatorios.
     * @param coeficiente el coeficiente del polinomio.
     * @param grado el grado del polinomio.
     */
    public Polinomio(BigInteger K, int grado){
        Random r = new Random();
        this.grado = grado;
        coeficientes = new BigInteger[grado +1];
        coeficientes[0] = K;
        for(int i = 1; i < grado + 1; i++){
            coeficientes[i] = new BigInteger(78, r);
            if(r.nextBoolean())
                coeficientes[i] = coeficientes[i].negate();
        }
    }

    /** 
     * Metodo que asigna un coeficiente a un grado dado.
     * @param coeficiente el coeficiente del polinomio.
     * @param grado el grado del polinomio.
     * @throws IllegalArgumentException si el grado es mayor al grado del polinomio.
     */
    public void setCoeficiente(BigInteger coeficiente, int grado){
        if(grado > this.grado)
            throw new IllegalArgumentException("El grado no puede ser mayor al grado del polinomio.");
        coeficientes[grado] = coeficiente;
    }

    /** 
     * Metodo que regresa el coeficiente de un grado dado.
     * @param grado el grado del polinomio.
     * @return el coeficiente del polinomio.
     */
    public BigInteger getCoeficiente(int grado){
        return coeficientes[grado];
    }

    /** 
     * Metodo que regresa el grado del polinomio.
     * @return el grado del polinomio.
     */
    public int getGrado(){
        return grado;
    }


    public String toString(){
        String s = "";
        for(int i = 0; i < grado + 1; i++){
            s += coeficientes[i].toString() + "x^" + i + " + ";
        }
        return s;
    }


    /**
     * Método que evalúa el polinomio en un valor dado.
     * @param x el valor en el que se evaluará el polinomio.
     * @return el valor del polinomio evaluado en x.
     */
    public BigInteger evalua(BigInteger x){
        BigInteger factor = new BigInteger("1");
        BigInteger resultado = new BigInteger("0");
        for(int i = 0 ; i < grado; i++){
            resultado = resultado.add(coeficientes[i].multiply(factor));
            factor = factor.multiply(x);
        }
        return resultado;
    }

    /**
     * 
     * Realiza una version simplificada de la interpolacion
     * de polinomios de lagrange para obtener el termino 
     * independiente de un polinomio dado n puntos.
     */
    public static BigInteger interpolacion(ParOrdenado<BigInteger>[] puntos){
        int n = puntos.length;
        BigInteger k= new BigInteger("0");
        BigInteger num= new BigInteger("1");
        BigInteger den = new BigInteger("1");
        for(int i = 0; i < n; i++){
            num = puntos[i].getY().mod(MODP);
            for(int j = 0; j < n; j++){
                if(i == j)
                    continue;
                num = num.multiply(puntos[j].getX()).mod(MODP);
                den = den.multiply(puntos[i].getX().subtract(puntos[j].getX())).mod(MODP);
            }
            BigInteger div = num.multiply(den.modInverse(MODP)).mod(MODP);
            k = k.add(div).mod(MODP);
            num = new BigInteger("1");
            den = new BigInteger("1");
        }
        if(n % 2 == 0)
            k = k.negate();
        return k;
    }

    /**
     * Realiza una evaluacion de un polinomio en n puntos aleatorios. Pero 
     * distintos de cero y distintos entre si.
     * @param n el numero de puntos a evaluar.
     * @return un arreglo de n puntos aleatorios.
     */
    public List<ParOrdenado<BigInteger>> generaPuntos(int n){
        List<ParOrdenado<BigInteger>> puntos = new List<ParOrdenado<BigInteger>>();
        Set<BigInteger> set = new HashSet<BigInteger>();
        BigInteger x = new BigInteger(78, new Random());
        while(set.size() < n){
            while(x.equals(new BigInteger("0")))
                x = new BigInteger(78, new Random());
            set.add(x);
        }
        for(BigInteger xi: set)
            puntos.add(new ParOrdenado<BigInteger>(xi, evalua(xi)));
        return puntos;
    }
}