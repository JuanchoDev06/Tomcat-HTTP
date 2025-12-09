package co.edu.poli.ces3.universitas.repositories;

import co.edu.poli.ces3.universitas.dto.Subject;
import com.google.gson.JsonObject;
import java.util.Vector;

public class SubjectRepository extends MySQLConnector implements Crud<Subject, String>{

    private Vector<Subject> subjects;

    public SubjectRepository() {
        subjects = new Vector<>();
        subjects.add(new Subject("APL-123", new StringBuilder("CES3")));
        subjects.add(new Subject("DT-13", new StringBuilder("Base de dato 2")));
    }

    @Override
    void disconnect() {
        System.out.println("Disconnecting");
    }


    @Override
    public Subject create(JsonObject jsonObject) {

        Subject s = new Subject();
        s.setId(jsonObject.get("id").getAsString());
        s.setName(jsonObject.get("name").getAsString());
        s.setCode(jsonObject.get("code").getAsString());
        s.setDescription(new StringBuilder(jsonObject.get("description").getAsString()));

        subjects.add(s);
        return s;
    }

    @Override
    public void update(Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId().equals(subject.getId())) {
                subjects.set(i, subject);
                return;
            }
        }
    }

    @Override
    public void delete(String id){
        subjects.removeIf(subject -> subject.getId().equals(id));
    }

    @Override
    public Vector<Subject> findAll() {
        return subjects;
    }

    @Override
    public Subject findById(String id) {
        return subjects.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
