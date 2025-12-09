package co.edu.poli.ces3.universitas.repositories;

import co.edu.poli.ces3.universitas.dto.Student;
import com.google.gson.JsonObject;

import java.util.Vector;

public class StudentRepository extends MySQLConnector implements Crud<Student, String> {

    private Vector<Student> students;

    public StudentRepository() {
        students = new Vector<>();

        // Datos de ensayo
        Student s1 = new Student();
        s1.setID("STU-001");
        s1.setName("Juan");
        s1.setLastName("Correa");
        s1.setAge(20);
        s1.setMarried(false);

        Student s2 = new Student();
        s2.setID("STU-002");
        s2.setName("Oscar");
        s2.setLastName("Mesa");
        s2.setAge(22);
        s2.setMarried(false);

        students.add(s1);
        students.add(s2);
    }

    @Override
    void disconnect() {
        System.out.println("Disconnecting");
    }

    @Override
    public Student create(JsonObject jsonObject) {

        Student s = new Student();

        s.setID(jsonObject.get("id").getAsString());
        s.setName(jsonObject.get("name").getAsString());
        s.setLastName(jsonObject.get("lastName").getAsString());
        s.setAge(jsonObject.get("age").getAsInt());
        s.setMarried(jsonObject.get("isMarried").getAsBoolean());

        students.add(s);
        return s;
    }

    @Override
    public void update(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(student.getID())) {
                students.set(i, student);
                return;
            }
        }
    }

    @Override
    public void delete(String id) {
        students.removeIf(s -> s.getID().equals(id));
    }

    @Override
    public Vector<Student> findAll() {
        return students;
    }

    @Override
    public Student findById(String id) {
        return students.stream()
                .filter(s -> s.getID().equals(id))
                .findFirst()
                .orElse(null);
    }



}
