package org.example.services.student;

import org.example.model.Entity.EntityStudent;
import org.example.model.response.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    boolean insertStudent(EntityStudent student);
    List<StudentResponse> getStudents();
    boolean updateStudentStatus(EntityStudent student);
    boolean updateImage(EntityStudent student);
    StudentResponse getStudentByID(int id);
}
