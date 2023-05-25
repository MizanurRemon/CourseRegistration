package org.example.services.Admin;

import jakarta.transaction.Transactional;
import org.example.dao.admin.AdminDao;
import org.example.model.Entity.EntityAdmin;
import org.example.model.response.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AdminServicesImpl implements AdminServices{

    @Autowired
    AdminDao dao;
    @Override
    public AdminResponse getAdmin(EntityAdmin entityAdmin) {
        return dao.getAdmin(entityAdmin);
    }

    @Override
    public AdminResponse checkAdmin(String username) {
        return dao.checkAdmin(username);
    }
}
