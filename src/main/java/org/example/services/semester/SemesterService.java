package org.example.services.semester;

import org.example.model.Entity.EntitySemester;
import org.example.model.response.SemesterResponse;

import java.util.List;

public interface SemesterService {
    boolean insertSemester(EntitySemester entitySemester);

    List<SemesterResponse> getSemester();

    boolean updateSemesterStatus(EntitySemester semester);

    boolean deleteSemester(int id);

    boolean updateSemester(EntitySemester semester);
}
