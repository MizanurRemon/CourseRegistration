package org.example.services.student;


import jakarta.transaction.Transactional;
import org.example.dao.student.StudentDao;
import org.example.model.Entity.EntityStudent;
import org.example.model.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao dao;

    @Override
    public boolean insertStudent(EntityStudent student) {
        return dao.insertStudent(student);
    }

    @Override
    public List<StudentResponse> getStudents() {
        return dao.getStudents();
    }

    @Override
    public boolean updateStudentStatus(EntityStudent student) {
        return dao.updateStudentStatus(student);
    }

    @Override
    public boolean updateImage(EntityStudent student) {
        return dao.updateImage(student);
    }
}
