package org.example.dao.course_registration;

import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntityCourseRegistration;
import org.example.model.response.CourseRegistrationResponse;
import org.example.model.response.CourseResponse;
import org.example.model.response.SemesterResponse;
import org.example.model.response.StudentResponse;
import org.example.utils.Constants;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseRegistrationDaoImpl implements CourseRegistrationDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    @Override
    public boolean courseRegistration(EntityCourseRegistration courseRegistration) {
        String checkQuery = "SELECT * from " + TableConstants.TBL_ASSIGNED_COURSES +
                " WHERE student_id ='" + courseRegistration.getStudent_id() +
                "' AND course_id = '" + courseRegistration.getCourse_id() +
                "' AND semester_id='" + courseRegistration.getSemester_id() + "'";


        List<Integer> rowCount = jdbcTemplate.query(checkQuery, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rowNum;
            }
        });

        if (rowCount.size() > 0) {

            throw new ApiRequestException("course registered in same semester");
        } else {
            String query = "INSERT into " + TableConstants.TBL_ASSIGNED_COURSES + " " +
                    "(student_id, semester_id, course_id,  status, created_at) " +
                    "values(?,?,?,?,?)";
            try {
                return jdbcTemplate.update(query, courseRegistration.getStudent_id(), courseRegistration.getSemester_id(), courseRegistration.getCourse_id(), courseRegistration.getStatus(), courseRegistration.getCreated_at()) == 1;
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
            }

        }
    }

    @Override
    public List<CourseRegistrationResponse> getRegisteredCourse() {
        String query = "SELECT ac.id as id, ac.status as status, ac.created_at as created_at, ac.updated_at as updated_at, " +
                "c.id as course_id, c.title as c_title,c.credits as c_credits, c.status as c_status, " +
                "s.id as s_id, s.name as s_name, s.phone as s_phone, s.roll_no as s_roll_no, s.status as s_status,s.image as s_image, s.created_at as s_created_at, s.updated_at as s_updated_at, " +
                "sem.id as sem_id, sem.title as sem_title, sem.status as sem_status " +
                "from registered_courses ac " +
                "LEFT JOIN courses c " +
                "ON ac.course_id = c.id " +
                "LEFT JOIN student s " +
                "ON ac.student_id = s.id " +
                "LEFT JOIN semester sem " +
                "ON ac.semester_id = sem.id";
        try {
            return jdbcTemplate.query(query, new RowMapper<CourseRegistrationResponse>() {
                @Override
                public CourseRegistrationResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

                    CourseRegistrationResponse response = new CourseRegistrationResponse();
                    response.id = rs.getInt("id");
                    response.status = rs.getString("status");
                    response.created_at = rs.getString("created_at");
                    response.updated_at = rs.getString("updated_at");

                    CourseResponse courseResponse = new CourseResponse();
                    courseResponse.id = rs.getInt("course_id");
                    courseResponse.title = rs.getString("c_title");
                    courseResponse.credits = rs.getInt("c_credits");
                    courseResponse.status = rs.getString("c_status");

                    SemesterResponse semesterResponse = new SemesterResponse();
                    semesterResponse.id = rs.getInt("sem_id");
                    semesterResponse.title = rs.getString("sem_title");
                    semesterResponse.status = rs.getString("sem_status");

                    StudentResponse studentResponse = new StudentResponse();
                    studentResponse.id = rs.getInt("s_id");
                    studentResponse.name = rs.getString("s_name");
                    studentResponse.phone = rs.getString("s_phone");
                    studentResponse.roll_no = rs.getString("s_roll_no");
                    studentResponse.status = rs.getString("s_status");
                    studentResponse.image = rs.getBytes("s_image");
                    studentResponse.created_at = rs.getString("s_created_at");
                    studentResponse.updated_at = rs.getString("s_updated_at");

                    response.course = courseResponse;
                    response.semester = semesterResponse;
                    response.student = studentResponse;
                    return response;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
