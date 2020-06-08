package modelo;

import excepciones.NoPortatilAulaException;

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
        String sql = "select * from portatil where id = ? ;";
        Portatil portatil = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                portatil = new Portatil(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4));
        } catch (SQLException | NoPortatilAulaException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return portatil;
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
        } catch (SQLException | NoPortatilAulaException e) {
            e.printStackTrace();
        }
        return listaPortatiles;
    }

    @Override
    public boolean actualizarRAMySSDPortatil(Portatil portatil) {
        String sql = "update portatil set ram = ?, ssd = ? where id = ?;";
        int resultado = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
            preparedStatement.setInt(1, portatil.getRam());
            preparedStatement.setInt(2, portatil.getSsd());
            preparedStatement.setInt(3, portatil.getId());
            resultado = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado == 1;
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
        String sql = "select * from portatil where ram >= ?;";
        List<Portatil> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, ram);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lista.add( new Portatil(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException | NoPortatilAulaException e) {
            e.printStackTrace();
        }  finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public static void main(String[] args) throws NoPortatilAulaException {
        PortatilDAO portatilDAO = new PortatilDAO();
       /* Portatil portat = new Portatil(12,100 , 10);
        portatilDAO.crearPortatil(portat);
        System.out.println("Existo en borrado: " + portatilDAO.borrarPortatilPorId(100));
        List<Portatil> lista = portatilDAO.obtenerTodosPortatiles();
        for (Portatil portatil : lista) {
            System.out.println(portatil);
        }*/
     //   System.out.println(portatilDAO.otenerPortatilPorId(2));
      /*  List<Portatil> lista = portatilDAO.obtenerPortatilesRAMSuperiorAoIgual(32);
        for (Portatil portatil: lista) {
            System.out.println(portatil.getId());
        }*/
        System.out.println(portatilDAO.actualizarRAMySSDPortatil(new Portatil(2, 256, 1000, 0)));
        System.out.println(portatilDAO.otenerPortatilPorId(2));
        Conexion.cierreConexion();
    }
}
