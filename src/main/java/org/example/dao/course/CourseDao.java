package org.example.dao.course;

import org.example.model.Entity.EntityCourse;
import org.example.model.response.CourseResponse;

import java.util.List;

public interface CourseDao {
    boolean insertCourse(EntityCourse entityCourse);

    List<CourseResponse> getCourses();

    boolean updateCourseStatus(EntityCourse entityCourse);
    boolean deleteCourse(int id);
}
