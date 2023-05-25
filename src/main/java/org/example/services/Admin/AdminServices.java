package org.example.services.Admin;

import org.example.model.Entity.EntityAdmin;
import org.example.model.response.AdminResponse;

public interface AdminServices {
    AdminResponse getAdmin(EntityAdmin entityAdmin);

    AdminResponse checkAdmin(String username);
}
