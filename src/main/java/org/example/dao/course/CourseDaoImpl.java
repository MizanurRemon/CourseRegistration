package org.example.dao.course;

import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntityCourse;
import org.example.model.response.CourseResponse;
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
public class CourseDaoImpl implements CourseDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    @Override
    public boolean insertCourse(EntityCourse entityCourse) {
        String checkQuery = "SELECT * from " + TableConstants.TBL_COURSE + " WHERE title = '" + entityCourse.getTitle() + "'";
        String query = "INSERT into " + TableConstants.TBL_COURSE + " (" + TableColumnConstants.TITLE + "," + TableColumnConstants.CREDITS + "," + TableColumnConstants.STATUS + ") values(?,?,?)";
        try {

            List<Integer> items = jdbcTemplate.query(checkQuery, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt(TableColumnConstants.ID);
                }
            });

            if (items.size() > 0) {
                throw new ApiRequestException(Constants.TITLE_EXIST);
            } else {
                return jdbcTemplate.update(query, entityCourse.getTitle(), entityCourse.getCredits(), entityCourse.getStatus()) == 1;

            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<CourseResponse> getCourses() {
        String query = "SELECT * from " + TableConstants.TBL_COURSE + " WHERE status = 'active'" +
                " ORDER BY id ASC";
        try {
            return jdbcTemplate.query(query, new RowMapper<CourseResponse>() {
                @Override
                public CourseResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CourseResponse course = new CourseResponse();
                    course.id = rs.getInt(TableColumnConstants.ID);
                    course.title = rs.getString(TableColumnConstants.TITLE);
                    course.credits = rs.getInt(TableColumnConstants.CREDITS);
                    course.status = rs.getString(TableColumnConstants.STATUS);

                    return course;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateCourseStatus(EntityCourse entityCourse) {
        String query = "UPDATE " + TableConstants.TBL_COURSE +
                " SET status = ? WHERE id = ?";

        try {
            return jdbcTemplate.update(query, entityCourse.getStatus(), entityCourse.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean deleteCourse(int id) {
        String query = "UPDATE " + TableConstants.TBL_COURSE +
                " SET status = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, "delete", id) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<CourseResponse> getCoursesAdmin() {
        String query = "SELECT * from " + TableConstants.TBL_COURSE + " ORDER BY id ASC";
        try {
            return jdbcTemplate.query(query, new RowMapper<CourseResponse>() {
                @Override
                public CourseResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CourseResponse course = new CourseResponse();
                    course.id = rs.getInt(TableColumnConstants.ID);
                    course.title = rs.getString(TableColumnConstants.TITLE);
                    course.credits = rs.getInt(TableColumnConstants.CREDITS);
                    course.status = rs.getString(TableColumnConstants.STATUS);

                    return course;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateCourse(EntityCourse course) {
        String query = "UPDATE " + TableConstants.TBL_COURSE +
                " SET title = ?, credits = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, course.getTitle(), course.getCredits(), course.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
