package mx.unam.ciencias.myd.proyecto3;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Clase para aplicaciones de archivos mze.</p>
 *
 * <p>La clase cuenta con dos modos, dependiendo de los argumentos
 * ejecuta el modo para resolver laberintos leidos de la entrada 
 * estandar o el modo para generar laberintos dados los parametros
 * escritos como argumentos de la aplicación.</p>
 */     

public class AplicacionShamir {

    /** El modo de la aplicación, verdadero es que cifra (1) y falso es que descifra (2). */
    private boolean descifra;
    /** El cifrador. */
    private CifradorShamir cifrador;
    /** El descifrador. */
    private DescifradorShamir descifrador;
    /** Llave de cifrado de AES obtenida del hash 
     * de la contraseña con SHA-256.*/  
    private long key;
    /** El número de evaluaciones del polinomio. */
    
    /** El número total de evaluaciones requeridas. */

    /** Lista de pares ordenados de evaluaciones */
    private List<ParOrdenado<Double>> evaluaciones;
    /** Ruta del archivo util */
    public String rutaArchivo ;

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
        if(args[0].toLowerCase().equals("-c")){
            this.cifrador = construyeCifrador(args);
        }else{
            this.descifrador = construyeDescifrador(args);
        }
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
    private CifradorShamir construyeCifrador(String[] args){
        // Codigo pendiente
        this.descifra = false;
        return null;
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
    private DescifradorShamir construyeDescifrador(String[] args){
        // Codigo pendiente
        this.descifra = true;
        consigueEvaluaciones(args[1]);
        return null;
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

    /** Modo cifra de la aplicación. .*/
    private void cifra() {
        cifrador.setPassword();
        cifrador.cifra();
    }

    /** Modo descifra de la aplicación. */
    private void descifra() {
        descifrador.descifra();
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
                // Divide la línea en dos números separados por espacio
                String[] partes = linea.split(" ");
                
                // Convierte las partes a tipo numérico (puedes ajustar esto según tus necesidades)
                double x = Double.parseDouble(partes[0]);  /////////// NOTAAAAAAA aqui hay que usar bigints
                double y = Double.parseDouble(partes[1]);
                
                // Crea un nuevo ParOrdenado y agrégalo a la lista
                ParOrdenado<Double> par = new ParOrdenado<>(x, y);
                evaluaciones.add(par);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores de lectura del archivo
        }

        // Imprimir la lista de pares ordenados
        /*for (ParOrdenado<Double> par : evaluaciones) 
            System.out.println(par);*/
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
            // Obtener la ruta del archivo sin extensión
            Path rutaSinExtension = obtenerRutaSinExtension(rutaArchivo);

            // Escribir el contenido en el nuevo archivo
            Files.write(rutaSinExtension, contenido.getBytes());
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores de entrada o salida
        }
    }

    /**
     * Metodo auxiliar que obtiene la ruta de un archivo sin su extensión.
     * @param rutaArchivo el nombre del archivo.
     * @return la ruta del archivo sin extensión.
     */
    private Path obtenerRutaSinExtension(String rutaArchivo) {
        // Obtener la ruta del archivo como objeto Path
        Path rutaOriginal = Paths.get(rutaArchivo);

        // Obtener el nombre del archivo sin extensión
        String nombreSinExtension = quitarExtension(rutaOriginal.getFileName().toString());

        // Construir la ruta del archivo sin extensión
        return rutaOriginal.resolveSibling(nombreSinExtension);
    }

    /**
     * Metodo auxiliar que quita la extensión de un nombre de archivo.
     * @param nombreArchivo el nombre del archivo.
     * @return el nombre del archivo sin extensión.
     */
    private String quitarExtension(String nombreArchivo) {
        // Encontrar la última aparición del punto (.) para obtener la extensión
        int index = nombreArchivo.lastIndexOf('.');
        if (index > 0) {
            return nombreArchivo.substring(0, index);
        }
        return nombreArchivo; // Si no hay punto, devolver el nombre original
    }


    /**
     * Metodo auxiliar que escribe el arreglo de pares ordenados en un archivo.
     * @param rutaArchivo el nombre del archivo a escribir.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public void escribirParesOrdenados(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (ParOrdenado<Double> par : evaluaciones) {
                // Escribe cada par en una línea con el formato "x y"
                writer.write(par.getX() + " " + par.getY());
                writer.newLine(); // Agrega un salto de línea después de cada par
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}