package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Connection conexion = null;
    //patrón singleton, instancia única
    private Conexion () {
        try {
            //myslq
            //connection = DriverManager.getConnection(driver + url + host+ usuario + password",
            Properties properties = new Properties();
            properties.load(new FileReader("configuracion/bd_sqlite.propierties"));
            String driver = properties.getProperty("DRIVER");
            String url = properties.getProperty("URL");
            conexion = DriverManager.getConnection(driver + url);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConexion (){
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
    public static void cierreConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Cerrando la conexión");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cierreConexion();
        System.out.println(Conexion.getConexion());
        System.out.println(getConexion());
        System.out.println(getConexion());
        cierreConexion();
    }
}
