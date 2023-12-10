package mx.unam.ciencias.myp.proyecto3.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import mx.unam.ciencias.myp.proyecto3.*;

/**
 * Clase para las pruebas unitarias de {@link DecifradorAES}
 */
public class TestDecifradorAES {

    @Test
    public void testDecifrar() {

        String[] contrasCifrado = { "contra", "123", "321", "CONTRA", "", "@@pfñq", "**¨{{a}" };
        String[] contrasDecifrado = { "contrA", "124", "32", "COTRA", "ladsjfjaf", "30'¿3", "" };

        for (int i = 0; i < contrasCifrado.length; i++)
            auxTestDecifrar(contrasCifrado[i], contrasCifrado[i], true);

        for (int i = 0; i < contrasCifrado.length; i++)
            auxTestDecifrar(contrasCifrado[i], contrasDecifrado[i], false);

    }

    /**
     * Metodo auxiliar para comprobar si decifrar la información con la misma
     * contraseña o con distinta contraseña
     * la misma contraseña funciona como debería
     * 
     * @param contraCifrado   la contraseña para cifrar
     * @param contraDecifrado la contraseña para decifrar
     */
    private void auxTestDecifrar(String contraCifrado, String contraDecifrado, boolean mismaContra) {

    }

}
