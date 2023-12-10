package mx.unam.ciencias.myp.proyecto3.test;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.Random;

import mx.unam.ciencias.myp.proyecto3.ParOrdenado;
import mx.unam.ciencias.myp.proyecto3.Polinomio;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p> Clase para probar la clase Polinomio.</p>
 * 
 * <p> La clase prueba los metodos de la clase Polinomio en casos extremos con numeros enteros
 * muy grandes.</p>
 */
public class TestPolinomio {

    /** Timeout para que no se trabe en caso de falla. */
    @Rule public Timeout globalTimeout = Timeout.seconds(100);

    /** Objeto Random para generar numeros aleatorios. */
    private static Random random = new Random();

    /**
     * Metodo que genera un polinomio aleatorio de grado n. Con los
     * coeficientes aleatorios en un rango de 0 a 2^m.
     * @param n el grado del polinomio.
     * @param m la potencia de 2 del rango de los coeficientes.
     * @return el polinomio generado.
     */
    private static Polinomio generaPolinomio(int n, int m){
        BigInteger[] coeficientes = new BigInteger[n + 1];
        for(int i = 0; i < n + 1; i++){
            coeficientes[i] = new BigInteger(m, random);
            if(random.nextBoolean()){
                coeficientes[i] = coeficientes[i].negate();
            }
        }
        return new Polinomio(coeficientes);
    }
    

    /**
     * Test que verifica que se evalua correctamente un polinomio.
     */
    @Test
    public void testEvaluacionPolinomioAleatorio(){
        for(int a = 0; a < 1000; a++){
            int n = random.nextInt(10);
            int m = random.nextInt(10);
            Polinomio p = generaPolinomio(n, m);
            BigInteger x = new BigInteger(m, random);
            BigInteger y = p.evalua(x);
            BigInteger z = new BigInteger("0");
            for(int i = 0; i < n + 1; i++)
                z = z.add(p.getCoeficiente(i).multiply(x.pow(i)));
            Assert.assertEquals(y, z);
        }   
    }


    /**
     * Test que verifica que se evalua correctamente un polinomio. Con 
     * numeros muy grandes.
     */
    @Test
    public void testEvaluacionPolinomioAleatorioBig(){
        for(int a = 0; a < 1000; a++){
            int n = random.nextInt(100);
            int m = random.nextInt(256);
            Polinomio p = generaPolinomio(n, m);
            BigInteger x = new BigInteger(m, random);
            BigInteger evaluacion = p.evalua(x);
            BigInteger resultado = new BigInteger("0");
            for(int i = 0; i < n + 1; i++)
                resultado = resultado.add(p.getCoeficiente(i).multiply(x.pow(i)));
            Assert.assertEquals(evaluacion, resultado);
        }   
    }


    /**
     * Test que verifica que se cree un polinomio aleatorio correctamente.
     */
    @Test
    public void testPolinomioAleatorio(){
        BigInteger terminoInd = new BigInteger("123456789");
        Polinomio p = new Polinomio(terminoInd, 14);
        Assert.assertTrue(true);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testInterpolacion(){
        
        assertTrue( true );
    }

    
}
