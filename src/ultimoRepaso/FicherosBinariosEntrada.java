package ultimoRepaso;

import java.io.*;

public class FicherosBinariosEntrada {
    public static void main(String[] args) {
        //Crear el flujo entrada
        //mietras haya bits por leer, hacemos operaciones
        //cerramos el flujo
        String nombreFichero = "FICHEROS/Captura de pantalla de 2020-05-16 20-01-59.png";
        File inFile = new File(nombreFichero);
        File outFile = new File("FICHEROS/coopia.png");
        if (inFile.exists()) {
            System.out.printf("Leido fichero %s, con tamaño %d bytes%n", inFile.getName(), inFile.length());
        }
        else {
            System.out.println("No existe el fichero");
            System.exit(1);
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
            int byteLeido;
            while ((byteLeido = in.read()) != -1) {
                System.out.printf("Bytes leido: %d, %c%n", byteLeido, (char) byteLeido);
                out.write(byteLeido);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
