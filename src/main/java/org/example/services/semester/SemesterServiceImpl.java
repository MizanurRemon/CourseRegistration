package org.example.services.semester;

import jakarta.transaction.Transactional;
import org.example.dao.semester.SemesterDao;
import org.example.model.Entity.EntitySemester;
import org.example.model.response.SemesterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SemesterServiceImpl implements SemesterService{
    @Autowired
    SemesterDao dao;
    @Override
    public boolean insertSemester(EntitySemester entitySemester) {
        return dao.insertSemester(entitySemester);
    }

    @Override
    public List<SemesterResponse> getSemester() {
        return dao.getSemester();
    }

    @Override
    public boolean updateSemesterStatus(EntitySemester semester) {
        return dao.updateSemesterStatus(semester);
    }

    @Override
    public boolean deleteSemester(int id) {
        return dao.deleteSemester(id);
    }
}
