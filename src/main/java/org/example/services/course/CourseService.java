package org.example.services.course;

import org.example.model.Entity.EntityCourse;
import org.example.model.response.CourseResponse;

import java.util.List;

public interface CourseService {
    boolean insertCourse(EntityCourse entityCourse);

    List<CourseResponse> getCourses();
    boolean updateCourseStatus(EntityCourse entityCourse);
    boolean deleteCourse(int id);
}
