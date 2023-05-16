package org.example.services.course_registration;


import jakarta.transaction.Transactional;
import org.example.dao.course_registration.CourseRegistrationDao;
import org.example.model.Entity.EntityCourseRegistration;
import org.example.model.response.CourseRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseRegistrationServiceImpl implements CourseRegistrationService{

    @Autowired
    CourseRegistrationDao dao;
    @Override
    public boolean courseRegistration(EntityCourseRegistration courseRegistration) {
        return dao.courseRegistration(courseRegistration);
    }

    @Override
    public List<CourseRegistrationResponse> getRegisteredCourse() {
        return dao.getRegisteredCourse();
    }
}
