package mx.unam.ciencias.myd.proyecto3;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;

public class DecifradorAES {

    /** Tipo de cifrado para el documento */
    private static final String TIPO_DE_DECIFRADO = "AES/CBC/PKCS5Padding";

    /** Algoritmo de cifrado */
    private static final String ALGORITMO_CIFRADO = "AES";

    /** Dispersador para la contraseña */
    private DispersadorSHA256 dispersador;

    /** Contraseña dispersada con el algoritmo SHA-256 */
    private byte[] contraDispersada;

    /** Bytes del documento cifrado */
    private byte[] bytesDocumentoCifrado;

    /** Datos decifrados */
    private byte[] bytesDocumentoDecifrado;

    /** Documento cifrado */
    private Documento documentoCifrado;

    /**
     * Constructor
     * 
     * @param rutaDocumentoCifrado la dirección en la que está el documento cifrado
     * @param contraUsuario        la contraseña con la que se decifrará el
     *                             documento
     */
    public DecifradorAES(String rutaDocumentoCifrado, String contraUsuario) {
        dispersador = new DispersadorSHA256();
        contraDispersada = dispersador.dispersa(contraUsuario);
        documentoCifrado = new Documento(rutaDocumentoCifrado);
        bytesDocumentoCifrado = documentoCifrado.obtenBytesDocumento();

    }

    /**
     * Decifra el documento originalmente cifrado, para ello se asueme que en los
     * datos (bytes) cifrados inicialmente los primeros 16 corresponden al IV
     * (vector inicial), pues de otra forma no se podría decifrar correctamente
     */
    public void decifrar() {
        try {
            Cipher decifrador = Cipher.getInstance(TIPO_DE_DECIFRADO);

            byte[] iv = new byte[16];

            System.out.println("La longitud del arreglo de bytesDocumentosCifrado es: " + bytesDocumentoCifrado.length);

            System.arraycopy(bytesDocumentoCifrado, 0, iv, 0, 16);

            SecretKeySpec llave = new SecretKeySpec(contraDispersada, ALGORITMO_CIFRADO);
            decifrador.init(Cipher.DECRYPT_MODE, llave, new IvParameterSpec(iv));

            byte[] datosCifradosSinIV = new byte[bytesDocumentoCifrado.length - iv.length];
            System.arraycopy(bytesDocumentoCifrado, iv.length, datosCifradosSinIV, 0, datosCifradosSinIV.length);

            bytesDocumentoDecifrado = decifrador.doFinal(datosCifradosSinIV);

            documentoCifrado.escribeBytesDocumento(bytesDocumentoDecifrado);

        } catch (InvalidKeyException | BadPaddingException e) {
            System.err.println("La contraseña es inválida");
        } catch (Exception e) {
            System.err.println("Error durante el descifrado");
            e.printStackTrace();
        }
    }

}
