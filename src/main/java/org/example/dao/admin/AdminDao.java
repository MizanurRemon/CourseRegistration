package org.example.dao.admin;

import org.example.model.Entity.EntityAdmin;
import org.example.model.response.AdminResponse;

public interface AdminDao {
    AdminResponse getAdmin(EntityAdmin entityAdmin);

    AdminResponse checkAdmin(String username);
}
