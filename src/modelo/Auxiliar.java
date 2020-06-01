package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static void leerDatosFicheroVolcarABd(String path, InterfazPortatilDAO dao) {

    }

    public static void main(String[] args) {
        volcarBDaFichero(new PortatilDAO());
        Conexion.cierreConexion();
    }
}
