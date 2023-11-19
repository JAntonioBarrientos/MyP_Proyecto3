package mx.unam.ciencias.myd.proyecto3;

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
        try{
            //AplicacionShamir aplicacionShamir = new aplicacionShamir(args);
            //aplicacionShamir.ejecuta();
            System.exit(0);
        } catch(ExcepcionFormatoInvalido efi){
            System.err.println(efi.getMessage());
            formato();
            System.exit(ERROR_FORMATO);
        }
        catch(ExcepcionArchivoInvalido eai){
            System.err.println(eai.getMessage());
            System.exit(ERROR_ARCHIVO);
        }
        catch(IllegalArgumentException iae){
            System.err.println(iae.getMessage());
            System.exit(ERROR_USO);
        }
    }
}