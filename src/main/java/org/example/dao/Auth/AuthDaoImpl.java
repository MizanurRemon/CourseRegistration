package org.example.dao.Auth;


import org.example.Handler.Error.ApiRequestException;
import org.example.configuration.JwtTokenProvider;
import org.example.helpers.Helpers;
import org.example.helpers.JwtAuth;
import org.example.model.response.AuthResponse;
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

           /* if (jdbcTemplate.queryForObject(checkRoll, Integer.class, roll) != 0) {

                int s_id = jdbcTemplate.queryForObject(checkRoll, Integer.class, roll);
                String checkAuth = "SELECT COUNT(id) from " + TableConstants.TBL_AUTH + " WHERE student_id = ?";


                if (jdbcTemplate.queryForObject(checkAuth, Integer.class, s_id) == 0) {

                    String insertToken = "INSERT into " + TableConstants.TBL_AUTH + " " +
                            "(student_id, token) values(?, ?)";

                    if (jdbcTemplate.update(insertToken, s_id, JwtAuth.convertIntoJwt(roll)) == 1) {

                        String getAuth = "SELECT * from " + TableConstants.TBL_AUTH + " WHERE student_id = '" + s_id + "'";
                        return jdbcTemplate.queryForObject(getAuth, new RowMapper<AuthResponse>() {
                            @Override
                            public AuthResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                                AuthResponse authResponse = new AuthResponse();
                                authResponse.id = rs.getInt("id");
                                authResponse.student_id = rs.getInt("student_id");
                                authResponse.token = rs.getString("token");
                                return authResponse;
                            }
                        });
                    } else {
                        throw new ApiRequestException("error");
                    }

                } else {
                    String updateToken = "UPDATE " + TableConstants.TBL_AUTH + " " +
                            "SET token = ? WHERE student_id = ?";

                    if (jdbcTemplate.update(updateToken, JwtAuth.convertIntoJwt(roll), s_id) == 1) {
                        String getAuth = "SELECT * from " + TableConstants.TBL_AUTH + " WHERE student_id = '" + s_id + "'";
                        return jdbcTemplate.queryForObject(getAuth, new RowMapper<AuthResponse>() {
                            @Override
                            public AuthResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                                AuthResponse authResponse = new AuthResponse();
                                authResponse.id = rs.getInt("id");
                                authResponse.student_id = rs.getInt("student_id");
                                authResponse.token = rs.getString("token");
                                return authResponse;
                            }
                        });
                    } else {
                        throw new ApiRequestException("error");
                    }

                }

            } else {
                throw new ApiRequestException("roll not found");
            }*/

            return jdbcTemplate.queryForObject(checkRoll, new RowMapper<AuthResponse>() {

                @Override
                public AuthResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AuthResponse response = new AuthResponse();
                    response.id = rs.getInt(TableColumnConstants.ID);
//                    response.token = JwtAuth.convertIntoJwt(roll);
                    response.token = JwtTokenProvider.generateToken(roll);
                    return response;
                }
            });


        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
