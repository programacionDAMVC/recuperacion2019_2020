package modelo;

import java.util.List;

public interface InterfazPortatilDAO {
    //CRUD: Create, Read, Update, Delete
    boolean crearPortatil(Portatil portatil);
    Portatil otenerPortatilPorId(int id);
    List<Portatil> obtenerTodosPortatiles();
    boolean actualizarPortatil(Portatil portatil);
    boolean borrarPortatilPorId (int id);
    List<Portatil> obtenerPortatilesRAMSuperiorAoIgual(int ram);
}
