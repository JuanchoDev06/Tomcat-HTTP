package co.edu.poli.ces3.universitas.services;

import co.edu.poli.ces3.universitas.dto.Subject;
import co.edu.poli.ces3.universitas.repositories.SubjectRepository;
import com.google.gson.JsonObject;
import jdk.internal.loader.AbstractClassLoaderValue;

import java.util.Vector;

public class SubjectService {

    private SubjectRepository repository;

    public SubjectService(){
        repository = new SubjectRepository(); //hereda valores del repository
    }

    public Vector<Subject> find(){
        return repository.findAll();
    }

    public Subject findById(String id){
        return repository.findById(id);
    }

    public Subject add(JsonObject jsonSubject){
        return repository.create(jsonSubject);
    }

    public Subject update(JsonObject jsonSubject){
        //Se valida la existencia del Subject
        Subject existing = repository.findById(jsonSubject.get("id").getAsString());

        if (existing == null) return null;

        existing.setName(jsonSubject.get("name").getAsString());
        existing.setCode(jsonSubject.get("code").getAsString());
        existing.setDescription(new StringBuilder(jsonSubject.get("description").getAsString()));

        repository.update(existing);

        return existing;
    }

    public Subject patch(JsonObject jsonSubject){
        //Se valida la existencia del Subject
        Subject existing = repository.findById(jsonSubject.get("id").getAsString());

        if (existing == null) return null;

        if (jsonSubject.has("name")) existing.setName(jsonSubject.get("name").getAsString());
        if (jsonSubject.has("code")) existing.setCode(jsonSubject.get("code").getAsString());
        if (jsonSubject.has("description"))
            existing.setDescription(new StringBuilder(jsonSubject.get("description").getAsString()));

        repository.update(existing);

        return existing;
    }

    public Subject delete(String id){
        Subject s = repository.findById(id);
        if (s != null){
            repository.delete(id);
        }
        return s;
    }
}

