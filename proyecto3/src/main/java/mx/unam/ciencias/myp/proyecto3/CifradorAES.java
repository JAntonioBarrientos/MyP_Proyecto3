package mx.unam.ciencias.myp.proyecto3;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;

/**
 * <p>
 * Clase para cifrar documentos con AES 256
 * </p>
 */
public class CifradorAES {

    /** Tipo de cifrado para el documento */
    private static final String TIPO_DE_CIFRADO = "AES/CBC/PKCS5Padding";

    /** Algoritmo de cifrado */
    private static final String ALGORITMO_CIFRADO = "AES";

    /** Extension del archivo cifrado. */
    private final String EXTENSION_CIFRADO = ".aes";

    /**
     * Contraseña dispersada con el algoritmo SHA-256 y transformada a su
     * represeentacion numérica
     */
    private BigInteger K;

    /** El documento a cifrar visto como un objeto Documento */
    private Documento documentoClaro;

    /** Contraseña dispersada con el algoritmo SHA-256 */
    private byte[] contraDispersada;

    /** La informacion a cifrar */
    private byte[] bytesDocumentoClaro;

    /** Ruta del documento cifrado */
    private String rutaDocumentoCifrado;

    /**
     * Constructor
     * 
     * @param rutaDocumentoClaro   la ruta del docuemnto a encriptar
     * @param contraUsuario        la contraseña con la que se encriptará el
     *                             documento
     * @param rutaDocumentoCifrado la ruta donde irá el documento encriptado
     */
    public CifradorAES(String rutaDocumentoClaro, String contraUsuario, String rutaDocumentoCifrado) {

        if (verificaArchivo(rutaDocumentoClaro)) {
            documentoClaro = new Documento(rutaDocumentoClaro);
            bytesDocumentoClaro = documentoClaro.leeBytesDocumento();
            contraDispersada = DispersadorSHA256.dispersa(contraUsuario);

            K = new BigInteger(contraDispersada);
            this.rutaDocumentoCifrado = rutaDocumentoCifrado;
        }

    }

    /**
     * Metodo para verificar que el archivo a cifrar exista o no esté vacio
     * 
     * @param rutaDocumento la ruta del documento a verificar
     * @return verdadero si el archivo cumple con lo anterior
     */
    private boolean verificaArchivo(String rutaDocumento) {
        File documento = new File(rutaDocumento);

        if (!documento.isFile())
            throw new ExcepcionArchivoInvalido("La ruta '" + rutaDocumento + "' no corresponde a un archivo");

        if (documento.length() == 0)
            throw new ExcepcionArchivoInvalido("El archivo en '" + rutaDocumento + "' está vacio");

        return true;
    }

    /**
     * Cifra un documento con AES-256, para ello hace uso de la contraseña
     * proporcionada por el usuario y de un vector de inicializacion aleatorio, el
     * cual será agregado a los datos cifrados (los primeros 16 bytes) para que
     * posterioremente estos puedan ser decifrados con el mismo
     * 
     * @throws IOException en caso de que algo salga mal al escribir el
     *                     archivo
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
        Documento documentoCifrado = new Documento(rutaDocumentoCifrado + EXTENSION_CIFRADO);
        documentoCifrado.escribeBytesDocumento(datosCifrados);
    }

    /**
     * Regresa la contraseña del usuario dispersada con SHA-256 y vista
     * como su reprsetnación numérica
     * 
     * @return la contraseña disperada y vista como un BigInteger
     */
    public BigInteger obtenSecreto() {
        return K;
    }

}
