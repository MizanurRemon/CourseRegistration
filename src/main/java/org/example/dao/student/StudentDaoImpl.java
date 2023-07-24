package org.example.dao.student;


import jakarta.persistence.Table;
import org.example.Handler.Error.ApiRequestException;
import org.example.helpers.Helpers;
import org.example.model.Entity.EntityStudent;
import org.example.model.response.StudentResponse;
import org.example.utils.Constants;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }


    @Override
    public boolean insertStudent(EntityStudent student) {
        String checkQuery = "SELECT * from " + TableConstants.TBL_STUDENT +
                " WHERE phone = '" + student.getPhone() + "' " +
                "OR roll_no = '" + student.getRoll_no() + "'";

        String query = "INSERT into " + TableConstants.TBL_STUDENT + " (name, phone, image, roll_no, status, created_at) values(?,?,?,?,?,?)";

        try {
            List<Integer> list = jdbcTemplate.query(checkQuery, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

                    return rs.getInt(TableColumnConstants.ID);
                }
            });

            if (list.size() > 0) {
                throw new ApiRequestException("user exist");
            }
            return jdbcTemplate.update(query, student.getName(), student.getPhone(), student.getImage(), student.getRoll_no(), "active", student.getCreated_at()) == 1;

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<StudentResponse> getStudents() {
        String query = "SELECT * from " + TableConstants.TBL_STUDENT + " " +
                "ORDER BY id DESC";

        try {
            return jdbcTemplate.query(query, new RowMapper<StudentResponse>() {
                @Override
                public StudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    StudentResponse response = new StudentResponse();
                    response.id = rs.getInt(TableColumnConstants.ID);
                    response.name = rs.getString(TableColumnConstants.NAME);
                    response.phone = rs.getString(TableColumnConstants.PHONE);
                    response.roll_no = rs.getString(TableColumnConstants.ROLL_NO);
                    response.status = rs.getString(TableColumnConstants.STATUS);
                    response.image = rs.getBytes(TableColumnConstants.IMAGE);
                    response.created_at = rs.getString(TableColumnConstants.CREATED_AT);
                    response.updated_at = rs.getString(TableColumnConstants.UPDATED_AT);

                    return response;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateStudentStatus(EntityStudent student) {
        String query = "UPDATE " + TableConstants.TBL_STUDENT + " SET status = ?, updated_at = ?" +
                " WHERE id = ?";

        try {
            return jdbcTemplate.update(query, student.getStatus(), Helpers.getCurrentTime(), student.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateImage(EntityStudent student) {
        String query = "UPDATE " + TableConstants.TBL_STUDENT + " SET image = ?, updated_at = ?" +
                " WHERE id = ?";
        try {
            return jdbcTemplate.update(query, student.getImage(), Helpers.getCurrentTime(), student.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public StudentResponse getStudentByID(int id) {
        String query = "SELECT * from student WHERE id = '" + id + "'";
        try {
            return jdbcTemplate.queryForObject(query, new RowMapper<StudentResponse>() {
                @Override
                public StudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    StudentResponse studentResponse = new StudentResponse();
                    studentResponse.id = rs.getInt(TableColumnConstants.ID);
                    studentResponse.name = rs.getString(TableColumnConstants.NAME);
                    studentResponse.phone = rs.getString(TableColumnConstants.PHONE);
                    studentResponse.roll_no = rs.getString(TableColumnConstants.ROLL_NO);
                    studentResponse.image = rs.getBytes(TableColumnConstants.IMAGE);
                    studentResponse.status = rs.getString(TableColumnConstants.STATUS);
                    studentResponse.created_at = rs.getString(TableColumnConstants.CREATED_AT);
                    studentResponse.updated_at = rs.getString(TableColumnConstants.UPDATED_AT);

                    return studentResponse;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateStudentDetails(EntityStudent student) {
        String query = "UPDATE " + TableConstants.TBL_STUDENT + " SET name = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, student.getName(), student.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
