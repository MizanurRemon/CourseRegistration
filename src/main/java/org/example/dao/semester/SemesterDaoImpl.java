package org.example.dao.semester;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntitySemester;
import org.example.model.response.SemesterResponse;
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
public class SemesterDaoImpl implements SemesterDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    @Override
    public boolean insertSemester(EntitySemester entitySemester) {
        String checkQuery = "SELECT * from " + TableConstants.TBL_SEMESTER + " WHERE title = '" + entitySemester.getTitle() + "'";

        String query = "INSERT into " + TableConstants.TBL_SEMESTER + " (" + TableColumnConstants.TITLE + "," + TableColumnConstants.STATUS + ")" +
                " values (?,?)";
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
                return jdbcTemplate.update(query, entitySemester.getTitle(), entitySemester.getStatus()) == 1;

            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<SemesterResponse> getSemester() {
        String query = "SELECT * from " + TableConstants.TBL_SEMESTER+
               // " WHERE status = 'active'" +
                " ORDER BY title ASC";

        try {
            return jdbcTemplate.query(query, new RowMapper<SemesterResponse>() {
                @Override
                public SemesterResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SemesterResponse response = new SemesterResponse();
                    response.id = rs.getInt(TableColumnConstants.ID);
                    response.title = rs.getString(TableColumnConstants.TITLE);
                    response.status = rs.getString(TableColumnConstants.STATUS);

                    return response;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateSemesterStatus(EntitySemester semester) {
        String query = "UPDATE " + TableConstants.TBL_SEMESTER +
                " SET status = ? WHERE id = ?";

        try {
            return jdbcTemplate.update(query, semester.getStatus(), semester.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean deleteSemester(int id) {
        String query = "UPDATE " + TableConstants.TBL_SEMESTER +
                " SET status = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, "delete", id) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
