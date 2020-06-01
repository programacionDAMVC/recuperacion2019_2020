package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortatilDAO implements InterfazPortatilDAO{
    Connection conexion = Conexion.getConexion();
    @Override
    public boolean crearPortatil(Portatil portatil) {
        PreparedStatement sentencia = null;
        int resultado = 0;
        String sql = "INSERT INTO portatil (ram, ssd, pantalla) VALUES (?, ?, ?);";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, portatil.getRam());
            sentencia.setInt(2, portatil.getSsd());
            sentencia.setInt(3, portatil.getPantalla());
            resultado = sentencia.executeUpdate();
        } catch  (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado == 1;
    }

    @Override
    public Portatil otenerPortatilPorId(int id) {
        //CASA
        return null;
    }

    @Override
    public List<Portatil> obtenerTodosPortatiles() {
        List<Portatil> listaPortatiles = new ArrayList<>();
        String sql = "SELECT * FROM portatil;";
        try (Statement sentecia = conexion.createStatement();
             ResultSet resultado = sentecia.executeQuery(sql)) {
            while (resultado.next()){
                listaPortatiles.add(new Portatil(resultado.getInt("id"), resultado.getInt("ram"),
                        resultado.getInt("ssd"), resultado.getInt("pantalla")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPortatiles;
    }

    @Override
    public boolean actualizarPortatil(Portatil portatil) {
        //CASA
        //acutalizar ram y sdd, portatil.getRam()
        return false;
    }

    @Override
    public boolean borrarPortatilPorId(int id) {
        int resultado = 0;
        PreparedStatement sentencia = null;
        String sql = "DELETE FROM portatil WHERE id = ?;";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id);
            resultado = sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado == 1;
    }

    @Override
    public List<Portatil> obtenerPortatilesRAMSuperiorAoIgual(int ram) {
        //CASA
        return null;
    }

    public static void main(String[] args) {
        PortatilDAO portatilDAO = new PortatilDAO();
        Portatil portat = new Portatil(12,100 , 10);
        portatilDAO.crearPortatil(portat);
        System.out.println("Existo en borrado: " + portatilDAO.borrarPortatilPorId(100));
        List<Portatil> lista = portatilDAO.obtenerTodosPortatiles();
        for (Portatil portatil : lista) {
            System.out.println(portatil);
        }

        Conexion.cierreConexion();
    }
}
