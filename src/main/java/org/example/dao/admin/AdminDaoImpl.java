package org.example.dao.admin;

import org.example.Handler.Error.ApiRequestException;
import org.example.configuration.JwtTokenProvider;
import org.example.model.Entity.EntityAdmin;
import org.example.model.response.AdminResponse;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminDaoImpl implements AdminDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    @Override
    public AdminResponse getAdmin(EntityAdmin entityAdmin) {
        String query = "SELECT * FROM " + TableConstants.TBL_ADMIN +
                " WHERE username = '" + entityAdmin.getUsername() + "' AND password = '" + entityAdmin.getPassword() + "'";

        //System.out.println(query);
        try {
            return jdbcTemplate.queryForObject(query, new RowMapper<AdminResponse>() {
                @Override
                public AdminResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminResponse response = new AdminResponse();
                    response.id = rs.getInt(TableColumnConstants.ID);
                    response.username = rs.getString(TableColumnConstants.USERNAME);
                    response.token = JwtTokenProvider.generateToken(entityAdmin.getUsername());

                    return response;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    public AdminResponse checkAdmin(String username) {
        String query = "SELECT * FROM " + TableConstants.TBL_ADMIN +
                " WHERE username = '" + username + "'";

        //System.out.println(query);
        try {
            return jdbcTemplate.queryForObject(query, new RowMapper<AdminResponse>() {
                @Override
                public AdminResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminResponse response = new AdminResponse();
                    response.id = rs.getInt(TableColumnConstants.ID);
                    response.username = rs.getString(TableColumnConstants.USERNAME);
                    return response;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }


}
