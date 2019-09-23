package dao;

import java.util.List;

public interface CRUD<Generico>{

    void Registrar(Generico gen) throws Exception;

    void Modificar(Generico gen) throws Exception;

    void Eliminar(Generico gen) throws Exception;

    List<Generico> lista() throws Exception;
}