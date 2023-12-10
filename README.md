# MyP_proyecto3 Secreto Compartido de Shamir

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

2. Cambiate al directorio con el proyecto:

```bash
   $ cd MyP_Proyecto3
```

3. Compila el codigo 

```bash
   $ mvn compile
```

4. Crea el .jar para correr el programa 
```bash
   $ mvn install
```

Corre el programa en modo encriptar
```bash
   $ java -jar target/proyecto3 -c <archivo_para_evaluaciones> <numero_de_evaluaciones> <numero_minimo_de_evaluaciones> <archivo_a_encriptar> 
```

Corre el programa en modo desencriptar 
```bash
   $ java -jar target/proyecto3 -d <ruta_archivo_con_evaluaciones> <ruta_archivo_encriptado>
```

## Clases auxiliares:

- Cipher https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
- MessageDigest https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html