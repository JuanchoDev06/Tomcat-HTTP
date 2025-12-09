package co.edu.poli.ces3.universitas.repositories;

import co.edu.poli.ces3.universitas.dto.Subject;
import com.google.gson.JsonObject;

import java.util.Vector;

public interface Crud <T, ID>{
    T create(JsonObject jsonObject);
    void update(T dto);
    void delete(ID id);
    Vector<T> findAll();
    T findById(ID id);
}
