package org.example.services.course_registration;

import org.example.model.Entity.EntityCourseRegistration;
import org.example.model.response.CourseRegistrationResponse;

import java.util.List;

public interface CourseRegistrationService {
    boolean courseRegistration(EntityCourseRegistration courseRegistration);

    List<CourseRegistrationResponse> getRegisteredCourse();
    boolean updateRegisteredCourseStatus(EntityCourseRegistration courseRegistration);
}
