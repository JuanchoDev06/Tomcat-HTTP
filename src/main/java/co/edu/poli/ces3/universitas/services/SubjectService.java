package co.edu.poli.ces3.universitas.services;

import co.edu.poli.ces3.universitas.dto.Subject;
import java.util.Vector;

public class SubjectService {

    private Vector<Subject> subjects;

    public SubjectService() {
        subjects = new Vector<>();
        subjects.add(new Subject("APL-123", new StringBuilder("CES3")));

    }
}

