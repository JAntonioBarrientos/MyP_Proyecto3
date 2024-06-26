package mx.unam.ciencias.myp.proyecto3;

import java.util.ArrayList;
import java.util.List;

import mx.unam.ciencias.myp.proyecto3.AplicacionShamir;

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
        System.err.printf("La forma de ejecucion es: <OPCION> <ARCHIVO> <ARCHIVO / INT>\n");
    }

    /**
     * Método principal.
     * Hacemos un chequeo rapido de la cantidad minima de argumentos.
     * Checamos tambien algunos errores comunes y ejecutamos la aplicación.
     * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.printf("Error: uso incorrecto de programa.\n");
            formato();
            System.exit(ERROR_USO);
        }
        try{
            AplicacionShamir aplicacionShamir = new AplicacionShamir(args);
            aplicacionShamir.ejecuta();
            System.exit(0);
        }catch(ExcepcionFormatoInvalido efi){
            System.err.printf("Error: %s\n",efi.getMessage());
            formato();
            System.exit(ERROR_FORMATO);
        }
        catch(ExcepcionArchivoInvalido eai){
            System.err.printf("Error: %s\n",eai.getMessage());
            System.exit(ERROR_ARCHIVO);
        }
        catch(IllegalArgumentException i){
            System.err.printf("Error: %s\n",i.getMessage());
        }
    }
}