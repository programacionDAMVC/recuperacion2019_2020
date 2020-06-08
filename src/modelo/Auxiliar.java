package modelo;

import excepciones.NoPortatilAulaException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class Auxiliar {
    public static void volcarBDaFichero (InterfazPortatilDAO dao) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FICHEROS/");
        stringBuilder.append("BD_");
        stringBuilder.append(LocalDateTime.now().toString());
        stringBuilder.append(".csv");
        String nombreFichero = stringBuilder.toString();
        System.out.println(nombreFichero);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nombreFichero)))) {
            List<Portatil> lista = dao.obtenerTodosPortatiles();
            for (Portatil portatil: lista) {
                out.println(portatil);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MÉTODO QUE LEEA EL FICHERO CSV Y VUELQUE LOS DATOS A LA BASE DE DATOS
    public static void leerDatosFicheroVolcarABD(String path, InterfazPortatilDAO dao) {
        /*try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNextLine()) {
                linea = in.nextLine();
                String[] tokens = linea.split(",");
                int ram = Integer.parseInt(tokens[1].trim());
                ................
             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            List<String> lineas = Files.readAllLines(Paths.get(path));
            for (String linea : lineas) {
                String[] tokens = linea.split(",");
                String sRam = tokens[1].trim();
                if (!sRam.matches("[0-9]+"))
                    continue;
                int ram = Integer.parseInt(sRam);
                String sSSD = tokens[2].trim();
                if (!sSSD.matches("[0-9]+"))
                    continue;
                int ssd = Integer.parseInt(sSSD);
                String sPantalla = tokens[3].trim();
                if (!sPantalla.matches("[0-9]+"))
                    continue;
                int pantalla = Integer.parseInt(sPantalla);

                try {
                    Portatil portatil = new Portatil(ram, ssd, pantalla);
                    dao.crearPortatil(portatil);
                } catch (NoPortatilAulaException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean validarID (int id) {
       // id: es un número pósitivo, empieza por 0, NO PUEDE SER NEGATIVO
        return id >= 0;
    }
    public static boolean validarRAM (int ram) {
        //ram: es un numéro positivo, distinto de cero, EMPIEZA POR 1 Y MÁXIMO 256
        return  ram >= 1 && ram <= 256;
    }
    public static boolean validarSSD (int ssd) {
    //ssd: es un numéro positivo, distinto de 0, MÍNIMO 128 MÁXIMO 5000
        return ssd >= 128 && ssd <= 5000;
    }
    public static boolean validarPulgadas (int pantalla) {
    //pantalla: es un número positivo, distinto de cero, EMPIEZA 10 PULGADAS Y MÁXIMO 32
        return pantalla >= 10 && pantalla <= 32;
    }
    public static boolean validarOrdenador (int id, int ram, int ssd, int pantalla) {
        return  validarID(id) && validarRAM(ram) && validarSSD(ssd) && validarPulgadas(pantalla);
    }


    public static void main(String[] args) {
      //  volcarBDaFichero(new PortatilDAO());
        PortatilDAO dao = new PortatilDAO();
        leerDatosFicheroVolcarABD("FICHEROS/BD_2020-06-08T11:06:02.913123.csv", dao);
        Conexion.cierreConexion();
    }
}
