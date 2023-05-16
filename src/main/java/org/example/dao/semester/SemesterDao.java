package org.example.dao.semester;

import org.example.model.Entity.EntitySemester;
import org.example.model.response.SemesterResponse;

import java.util.List;

public interface SemesterDao {
    boolean insertSemester(EntitySemester entitySemester);

    List<SemesterResponse> getSemester();

    boolean updateSemesterStatus(EntitySemester semester);

    boolean deleteSemester(int id);
}
