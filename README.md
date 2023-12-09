# MyP_proyecto2 Cobertura Nubosa

## Integrantes del Equipo
* Barrientos Sánchez José Antonio 423019269
* Morales Chaparro Gael Antonio - 320076972
* Sosa Romo Juan Mario - 320051926
* Pastor De La Cruz Miguel - 320125537

## Descripción
Este proyecto tiene como objetivo implementar el esquema de Shamir para compartir la clave necesaria para descifrar un archivo que suponemos 
contiene información confidencial. EL cifrado del documento claro se hará con el estadnar de cifrado de datos AES, con una clave de cifrado de 
256 bits, y para lograr esto se dispersará la contraseña dada por el usuario con la función de dispersión SHA-256. 



## Instrucciones
1. Clona el repositorio desde la terminal:

```bash
   $ git clone https://github.com/JAntonioBarrientos/MyP_Proyecto3.git
```

2. Crear un entorno virtual desde la carpeta del proyecto:

```bash
    $ cd MyP_proyecto02
    $ python -m venv venv
```
3. Activar el entorno virtual desde la carpeta del proyecto:

Linux:
```bash
    $ source venv/bin/activate 
```

Windows:
```bash
    $ venv\Scripts\activate 
```

4. Instalar las dependencias del proyecto: `requirements.txt`

```bash
    $ pip install -r requirements.txt
```

Nota: Se debe tener instalado python y pip. En caso de tener varias versiones de python especificar python3.


## Uso


Ejecute el programa principal main.py proporcionando el nombre del archivo de imagen de entrada la cual debe estar en la raiz del repositorio, en formato JPEG. Puedes incluir la bandera opcional "S", "s", "-s" o "-S"  al final para generar la imagen en blanco y negro.


Ejemplo:

```bash
python main.py IMAGEN.jpg S
python main.py IMAGEN.jpg s
python main.py IMAGEN.jpg

```

## Pruebas unitarias
Para correr las pruebas unitarias ejecute el comando:

```bash
python -m unittest tests.test_ManejadorEntrada
```


## Estructura del proyecto

1.  `main.py:` Programa principal para ejecutar el proyecto.
2.  `CalculadorCCI.py`: Calcula el Índice de Cobertura Nubosa (CCI) a partir de la imagen procesada.
3.  `Convolucionador.py`: Aplica una matriz de convolución a la imagen binaria.
4.  `FiltroRB_ClasificadorPixeles.py`: Clasifica los píxeles de la imagen como nubes o cielo.
5.  `ManejadorEntrada.py`: Gestiona la validación de la entrada, la comprobación de la bandera y las dimensiones de la imagen.
6.  `ProcesadorImagen_Macara.py`: Aplica una máscara a la imagen para cubrir solo la parte útil.

## Clases auxiliares:

- Cipher https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
- MessageDigest https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html






