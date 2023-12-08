package mx.unam.ciencias.myd.proyecto3;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

public class CifradorAES {

    /** Tipo de cifrado para el documento */
    private static final String TIPO_DE_CIFRADO = "AES/CBC/PKCS5Padding";

    /** Algoritmo de cifrado */
    private static final String ALGORITMO_CIFRADO = "AES";

    /** Extension del archivo cifrado. */
    private final String EXTENSION_CIFRADO = ".aes";

    /** Dispersador para la contraseña */
    private DispersadorSHA256 dispersador;

    /** Contraseña dispersada con el algoritmo SHA-256 */
    private byte[] contraDispersada;

    /** La informacion a cifrar */
    private byte[] bytesDocumentoClaro;

    /** Ruta del documento cifrado */
    private String rutaDocumentoCifrado;

    /**
     * Constructor
     * 
     * @param documentoClaro       el documento a encriptar
     * @param contraUsuario        la contraseña con la que se encriptará el
     *                             documento
     * @param rutaDocumentoCifrado la ruta donde se creará el documento cifrada
     */
    public CifradorAES(Documento documentoClaro, String contraUsuario, String rutaDocumentoCifrado) {
        bytesDocumentoClaro = documentoClaro.obtenBytesDocumento();
        dispersador = new DispersadorSHA256();
        contraDispersada = dispersador.dispersa(contraUsuario);
        this.rutaDocumentoCifrado = rutaDocumentoCifrado;
    }

    /**
     * Cifra un documento con AES-256, para ello hace uso de la contraseña
     * proporcionada por el usuario y de un vector de inicializacion aleatorio, el
     * cual será agregado a los datos cifrados (los primeros 16 bytes) para que
     * posterioremente estos puedan ser decifrados con el mismo
     * 
     * @throws IOException en caso de que algo salga mal al escribir el archivo
     */
    public void cifra() throws IOException {
        try {
            Cipher cifrador = Cipher.getInstance(TIPO_DE_CIFRADO);

            SecretKeySpec llave = new SecretKeySpec(contraDispersada, ALGORITMO_CIFRADO);
            cifrador.init(Cipher.ENCRYPT_MODE, llave, new SecureRandom());
            byte[] datos_cifrados = cifrador.doFinal(bytesDocumentoClaro);
            byte[] iv = cifrador.getIV();
            byte[] datosCifradosConIV = new byte[iv.length + datos_cifrados.length];

            System.arraycopy(iv, 0, datosCifradosConIV, 0, iv.length);
            System.arraycopy(datos_cifrados, 0, datosCifradosConIV, iv.length, datos_cifrados.length);

            escribeArchivoEncriptado(datosCifradosConIV);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException | IllegalBlockSizeException | IllegalStateException e) {
            throw new IOException("Error al cifrar los datos.", e);
        }
    }

    /**
     * Escribe el documento cifrado en la ruta dada
     * 
     * @param datosCifrados la informacion en bytes a escribir
     * @throws IOException en caso de que algo salga mal al escribir el archivo
     */
    private void escribeArchivoEncriptado(byte[] datosCifrados) throws IOException {
        try (FileOutputStream salida = new FileOutputStream(new File(rutaDocumentoCifrado + EXTENSION_CIFRADO))) {
            salida.write(datosCifrados);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo cifrado en " + rutaDocumentoCifrado);
        }
    }

    /**
     * Regresa la contraseña del usuario dispersada con SHA-256
     * 
     * @return la contraseña disperada en bytes
     */
    public byte[] obtenContraDispera() {
        return contraDispersada;
    }

}
