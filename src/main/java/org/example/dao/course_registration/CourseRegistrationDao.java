package org.example.dao.course_registration;

import org.example.model.Entity.EntityCourseRegistration;
import org.example.model.response.CourseRegistrationResponse;

import java.util.List;

public interface CourseRegistrationDao {
    boolean courseRegistration(EntityCourseRegistration courseRegistration);

    List<CourseRegistrationResponse> getRegisteredCourse();

    boolean updateRegisteredCourseStatus(EntityCourseRegistration courseRegistration);
}
