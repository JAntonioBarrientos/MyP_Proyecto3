package mx.unam.ciencias.myp.proyecto3;

import javax.crypto.Cipher;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static java.nio.file.StandardCopyOption.*;
import java.math.BigInteger;

import java.io.File;

/**
 * <p>
 * Clase para decifrar docuemntos encriptados con AES 256
 * </p>
 */
public class DecifradorAES {

    /** Tipo de cifrado para el documento */
    private static final String TIPO_DE_DECIFRADO = "AES/CBC/PKCS5Padding";

    /** Algoritmo de cifrado */
    private static final String ALGORITMO_CIFRADO = "AES";

    /** Extension del archivo cifrado. */
    private final String EXTENSION_CIFRADO = "aes";

    /** Contraseña dispersada con el algoritmo SHA-256 */
    private byte[] contraDispersada;

    /** Bytes del documento cifrado */
    private byte[] bytesDocumentoCifrado;

    /** Datos decifrados */
    private byte[] bytesDocumentoDecifrado;

    /** Ruta del documento cifrado */
    private String rutaDocumentoCifrado;

    /** Documento cifrado */
    private Documento documentoCifrado;

    /** Longitud en bytes del vector aleatorio para decifrar */
    private static final int LONGITUD_IV = 16;

    /**
     * Constructor
     * 
     * @param rutaDocumentoCifrado la dirección en la que está el documento cifrado
     * @param contraUsuario        la contraseña con la que se decifrará el
     *                             documento
     */
    public DecifradorAES(String rutaDocumentoCifrado, BigInteger K) {

        if (verificaArchivo(rutaDocumentoCifrado)) {
            this.rutaDocumentoCifrado = rutaDocumentoCifrado;
            contraDispersada = K.toByteArray();
            documentoCifrado = new Documento(rutaDocumentoCifrado);
            bytesDocumentoCifrado = documentoCifrado.leeBytesDocumento();
        }

    }

    /**
     * Metodo para verificar que el archivo a cifrar exista o no esté vacio
     * 
     * @param rutaDocumento la ruta del documento a verificar
     * @return verdadero si el archivo cumple con lo anterior
     */
    private boolean verificaArchivo(String rutaDocumento) throws ExcepcionArchivoInvalido {
        File documento = new File(rutaDocumento);

        if (!documento.isFile())
            throw new ExcepcionArchivoInvalido("La ruta '" + rutaDocumento + "' no corresponde a un archivo");

        if (documento.length() == 0)
            throw new ExcepcionArchivoInvalido("El archivo en '" + rutaDocumento + "' está vacio");

        if (!verificaExtension(rutaDocumento).equals(EXTENSION_CIFRADO))
            throw new ExcepcionArchivoInvalido(
                    "La extensión del archivo en la ruta " + rutaDocumento + " no es .aes");

        return true;
    }

    /**
     * Regresa la extension de una cadena que representa la ruta de un archivo
     * 
     * @param ruta la ruta del archivo
     * @return la extension del archivo
     */
    private String verificaExtension(String ruta) {
        String extension = "";

        int i = ruta.lastIndexOf('.');
        if (i > 0) {
            extension = ruta.substring(i + 1);
        }
        return extension;
    }

    /**
     * 
     * Decifra el documento originalmente cifrado, para ello se asueme que en los
     * datos (bytes) cifrados inicialmente los primeros 16 corresponden al IV
     * (vector inicial), pues de otra forma no se podría decifrar correctamente
     *
     * @throws InvalidKeyException en caso de error al decifrar los datos o por
     *                             contraseña invalida
     */
    public void decifrar() throws InvalidKeyException {
        try {
            Cipher decifrador = Cipher.getInstance(TIPO_DE_DECIFRADO);

            byte[] iv = new byte[LONGITUD_IV];

            System.arraycopy(bytesDocumentoCifrado, 0, iv, 0, LONGITUD_IV);

            SecretKeySpec llave = new SecretKeySpec(contraDispersada, ALGORITMO_CIFRADO);
            decifrador.init(Cipher.DECRYPT_MODE, llave, new IvParameterSpec(iv));

            byte[] datosCifradosSinIV = new byte[bytesDocumentoCifrado.length - LONGITUD_IV];
            System.arraycopy(bytesDocumentoCifrado, LONGITUD_IV, datosCifradosSinIV, 0, datosCifradosSinIV.length);

            bytesDocumentoDecifrado = decifrador.doFinal(datosCifradosSinIV);

            documentoCifrado.escribeBytesDocumento(bytesDocumentoDecifrado);

            Path ruta = Paths.get(rutaDocumentoCifrado);
            String nuevoNombre = quitaExtension(rutaDocumentoCifrado);

            Files.move(ruta, ruta.resolveSibling(nuevoNombre), REPLACE_EXISTING);

        } catch (InvalidKeyException | BadPaddingException e) {
            System.err.println("La contraseña es inválida");
            throw new InvalidKeyException();
        } catch (Exception e) {
            System.err.println("Error durante el descifrado");
            e.printStackTrace();
        }
    }

    /**
     * Quita la extension .aes a la cadena con la ruta del archivo
     * 
     * @param ruta la ruta original del archivo
     * @return la ruta del archivo con el nombre del archivo sin la
     *         extensión .aes
     */
    private String quitaExtension(String ruta) {
        int ultimoPunto = ruta.lastIndexOf(".");
        return ruta.substring(0, ultimoPunto);
    }

}
