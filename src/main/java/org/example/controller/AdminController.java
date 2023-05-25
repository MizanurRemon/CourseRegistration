package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntityAdmin;
import org.example.model.Entity.EntityCourse;
import org.example.services.Admin.AdminServices;
import org.example.services.course.CourseService;
import org.example.utils.Constants;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(UrlConstants.V2)
public class AdminController {

    @Autowired
    AdminServices services;

    @PostMapping(UrlConstants.ADMIN_LOGIN)
    public ResponseEntity<?> adminLogin(EntityAdmin entityAdmin) {
        try {
            if (entityAdmin.getUsername().isEmpty() || entityAdmin.getPassword().isEmpty()) {
                throw new ApiRequestException("empty field");
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put("statusCode", HttpStatus.OK.value());
                body.put("data", services.getAdmin(entityAdmin));

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

}
