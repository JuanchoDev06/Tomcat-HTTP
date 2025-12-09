package co.edu.poli.ces3.universitas.services;

import co.edu.poli.ces3.universitas.dto.Student;
import co.edu.poli.ces3.universitas.repositories.StudentRepository;
import com.google.gson.JsonObject;

import java.util.Vector;

public class StudentService {

    private StudentRepository repository;

    public StudentService() {
        repository = new StudentRepository();
    }

    public Vector<Student> find() {
        return repository.findAll();
    }

    public Student findById(String id) {
        return repository.findById(id);
    }

    public Student add(JsonObject jsonStudent) {
        return repository.create(jsonStudent);
    }

    public Student update(JsonObject jsonStudent) {
        // Se valida la existencia
        String id = jsonStudent.get("id").getAsString();
        Student existing = repository.findById(id);

        if (existing == null) return null;

        existing.setName(jsonStudent.get("name").getAsString());
        existing.setLastName(jsonStudent.get("lastName").getAsString());
        existing.setAge(jsonStudent.get("age").getAsInt());
        existing.setMarried(jsonStudent.get("isMarried").getAsBoolean());

        repository.update(existing);
        return existing;
    }

    public Student patch(JsonObject jsonStudent) {
        String id = jsonStudent.get("id").getAsString();
        Student existing = repository.findById(id);

        if (existing == null) return null;

        if (jsonStudent.has("name"))
            existing.setName(jsonStudent.get("name").getAsString());

        if (jsonStudent.has("lastName"))
            existing.setLastName(jsonStudent.get("lastName").getAsString());

        if (jsonStudent.has("age"))
            existing.setAge(jsonStudent.get("age").getAsInt());

        if (jsonStudent.has("isMarried"))
            existing.setMarried(jsonStudent.get("isMarried").getAsBoolean());

        repository.update(existing);
        return existing;
    }

    public Student delete(String id) {
        Student s = repository.findById(id);
        if (s != null) {
            repository.delete(id);
        }
        return s;
    }
}
