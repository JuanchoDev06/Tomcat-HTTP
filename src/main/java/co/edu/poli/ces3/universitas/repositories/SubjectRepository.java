package co.edu.poli.ces3.universitas.repositories;

import co.edu.poli.ces3.universitas.dto.Subject;
import com.google.gson.JsonObject;

import java.util.Vector;

public class SubjectRepository extends MySQLConnector implements Crud<Subject, String>{

    public SubjectRepository() {}

    @Override
    void disconnect() {
        System.out.println("Disconnecting");
    }


    @Override
    public Subject create(JsonObject jsonObject) {
        return null;
    }

    @Override
    public void update(Subject dto) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public Vector<Subject> findAll() {
        return null;
    }

    @Override
    public Subject findById(String s) {
        return null;
    }
}
