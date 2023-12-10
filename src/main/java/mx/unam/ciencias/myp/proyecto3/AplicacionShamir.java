package mx.unam.ciencias.myp.proyecto3;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.InvalidKeyException;
import java.util.Scanner;

/**
 * <p>Clase para aplicaciones de archivos mze.</p>
 *
 * <p>La clase cuenta con dos modos, dependiendo de los argumentos
 * ejecuta el modo para resolver laberintos leidos de la entrada 
 * estandar o el modo para generar laberintos dados los parametros
 * escritos como argumentos de la aplicación.</p>
 */     

public class AplicacionShamir {

    /** El modo de la aplicación, falso es que cifra y verdadero es que descifra. */
    private boolean descifra;
    /** Llave de cifrado de AES obtenida del hash 
     * de la contraseña con SHA-256.*/  
    private BigInteger key;
    /** El cifrador de AES. */
    private CifradorAES cifrador;
    /** El descifrador de AES. */
    private DecifradorAES descifrador;
    /** El número de evaluaciones del polinomio. */
    private int n;
    /** El número minimo de puntos para descifrar. */
    private int t;
    /** Lista de pares ordenados de evaluaciones */
    private List<ParOrdenado<BigInteger>> evaluaciones;
    /** Ruta del archivo claro. */
    public String rutaArchivoClaro;
    /** Ruta del archivo de evaluaciones. */
    public String rutaArchivoEvaluaciones;
    /** Ruta del archivo cifrado. */
    public String rutaArchivoCifrado;
    /** Contraseña para cifrar el archivo. */
    public String contra;

    /**
     * Construye una aplicacionShamir con base en los argumentos recibidos.
     * Si se recibe la opción c, se configura al modo cifra, si se recibe la opción d, se configura al modo descifra.
     * @param args arreglo de dimensiones variables recibido del metodo main.
     * @throws IllegalArgumentException si no se especifica un modo de ejecución
    */
    public AplicacionShamir(String[] args){
        this.evaluaciones = new ArrayList<>();
        if(!args[0].toLowerCase().equals("-c") && !args[0].toLowerCase().equals("-d"))
            throw new IllegalArgumentException("Modo de ejecución no válido.\n");
        if(args[0].toLowerCase().equals("-c") && args.length == 5){
            rutaArchivoEvaluaciones = args[1];
            int n = Integer.parseInt(args[2]);
            if(n <= 2)
                throw new IllegalArgumentException("El número de evaluaciones debe ser mayor a 2.\n");
            this.n = n;
            int t = Integer.parseInt(args[3]);
            if(t <= 1 || t > n)
                throw new IllegalArgumentException("El número minimo de puntos debe ser mayor a 1 y menor o igual al número de evaluaciones.\n");
            this.t = t;
            rutaArchivoClaro = args[4];
            descifra = false;
        }else if(args.length == 3){
            rutaArchivoEvaluaciones = args[1];
            rutaArchivoCifrado = args[2];
            descifra = true;
        }
        else{
            throw new IllegalArgumentException("Numero de argumentos invalido.\n");
        }
    }

    /**
    * Ejecuta el modo de la aplicación.
    */
    public void ejecuta(){
        if(descifra) 
            descifra();
        else
            cifra();
    }

    /** Modo cifra de la aplicación. . */
    private void cifra() {
        try {
            setPassword();
            cifrador = new CifradorAES(rutaArchivoClaro, contra, rutaArchivoClaro);
            cifrador.cifra();
            Polinomio p = new Polinomio(cifrador.obtenSecreto(), t-1);
            evaluaciones = p.generaPuntos(n);
            escribirParesOrdenados(rutaArchivoEvaluaciones);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**  Modo descifra de la aplicación. */
    private void descifra() {
        try {
            consigueEvaluaciones(rutaArchivoEvaluaciones);
            ParOrdenado<BigInteger>[] puntos = new ParOrdenado[evaluaciones.size()];
            int i = 0;
            for(ParOrdenado<BigInteger> par : evaluaciones){
                puntos[i] = par;
                i++;
            }
            key = Polinomio.interpolacion(puntos);
            descifrador = new DecifradorAES(rutaArchivoCifrado, Polinomio.MOD);
            descifrador.decifrar();
        } catch (InvalidKeyException e) {
            System.out.println("Ocurrió un error decifrando");
        }
    }

    /**
        Metodo que solicita y lee la contraseña de la entrada estandar.
    */
    private void setPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la contraseña para cifar el documento: ");
        contra = sc.nextLine();
        sc.close();
      }

    
    /**
     * Metodo auxiliar que lee un archivo y lo convierte en una arreglo de pares ordenados.
     * @param archivo el nombre del archivo a leer.
     * @return una lista de pares ordenados con el contenido del archivo.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    private void consigueEvaluaciones(String rutaArchivo){
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                BigInteger x =  new BigInteger (partes[0]);  
                BigInteger y = new BigInteger (partes[1]);
                ParOrdenado<BigInteger> par = new ParOrdenado<>(x, y);
                evaluaciones.add(par);
            }
        } catch (IOException e) {}
    }

    /**
     * Metodo auxiliar que lee un archivo y regresa su contenido como una cadena.
     * @param archivo el nombre del archivo a leer.
     * @return el contenido del archivo como una cadena.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public static String leerArchivo(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }
    
    /**
     * Metodo auxiliar que escribe una cadena en un archivo.
     * @param rutaArchivo el nombre del archivo a escribir.
     * @param contenido la cadena a escribir en el archivo.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public void escribirArchivoSinExtension(String rutaArchivo, String contenido) {
        try {
            Path rutaSinExtension = obtenerRutaSinExtension(rutaArchivo);
            Files.write(rutaSinExtension, contenido.getBytes());
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    /**
     * Metodo auxiliar que obtiene la ruta de un archivo sin su extensión.
     * @param rutaArchivo el nombre del archivo.
     * @return la ruta del archivo sin extensión.
     */
    private Path obtenerRutaSinExtension(String rutaArchivo) {
        Path rutaOriginal = Paths.get(rutaArchivo);
        String nombreSinExtension = quitarExtension(rutaOriginal.getFileName().toString());
        return rutaOriginal.resolveSibling(nombreSinExtension);
    }

    /**
     * Metodo auxiliar que quita la extensión de un nombre de archivo.
     * @param nombreArchivo el nombre del archivo.
     * @return el nombre del archivo sin extensión.
     */
    private String quitarExtension(String nombreArchivo) {
        int index = nombreArchivo.lastIndexOf('.');
        if (index > 0) {
            return nombreArchivo.substring(0, index);
        }
        return nombreArchivo; 
    }


    /**
     * Metodo auxiliar que escribe el arreglo de pares ordenados en un archivo.
     * @param rutaArchivo el nombre del archivo a escribir.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public void escribirParesOrdenados(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (ParOrdenado<BigInteger> par : evaluaciones) {
                writer.write(par.getX().toString() + " " + par.getY().toString());
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}