package org.example.dao.Auth;


import org.example.Handler.Error.ApiRequestException;
import org.example.configuration.JwtTokenProvider;
import org.example.helpers.Helpers;
import org.example.helpers.JwtAuth;
import org.example.model.response.AuthResponse;
import org.example.model.response.StudentResponse;
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
public class AuthDaoImpl implements AuthDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    @Override
    public AuthResponse studentLogin(String roll) {

        String checkRoll = "SELECT * from " + TableConstants.TBL_STUDENT + " WHERE roll_no = '" + roll + "'";

        try {
            return jdbcTemplate.queryForObject(checkRoll, new RowMapper<AuthResponse>() {

                @Override
                public AuthResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AuthResponse response = new AuthResponse();

                    response.token = JwtTokenProvider.generateToken(roll);

                    response.id = rs.getInt(TableColumnConstants.ID);
                    response.name = rs.getString(TableColumnConstants.NAME);
                    response.roll_no = rs.getString(TableColumnConstants.ROLL_NO);

                    return response;
                }
            });


        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
