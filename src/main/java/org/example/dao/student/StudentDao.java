package org.example.dao.student;

import org.example.model.Entity.EntityStudent;
import org.example.model.response.StudentResponse;

import java.util.List;

public interface StudentDao {

    boolean insertStudent(EntityStudent student);

    List<StudentResponse> getStudents();
    boolean updateStudentStatus(EntityStudent student);

    boolean updateImage(EntityStudent student);

}
