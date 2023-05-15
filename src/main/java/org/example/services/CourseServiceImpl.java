package org.example.services;

import jakarta.transaction.Transactional;
import org.example.dao.CourseDao;
import org.example.model.Entity.EntityCourse;
import org.example.model.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseDao courseDao;
    @Override
    public boolean insertCourse(EntityCourse entityCourse) {
        return courseDao.insertCourse(entityCourse);
    }

    @Override
    public List<CourseResponse> getCourses() {
        return courseDao.getCourses();
    }

    @Override
    public boolean updateCourseStatus(EntityCourse entityCourse) {
        return courseDao.updateCourseStatus(entityCourse);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseDao.deleteCourse(id);
    }
}
