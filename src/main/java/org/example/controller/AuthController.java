package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.services.Auth.AuthService;
import org.example.utils.Constants;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping(UrlConstants.STUDENT_LOGIN)
    public ResponseEntity<?> studentLogin(String roll) {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>();
            body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
            body.put(Constants.DATA, service.studentLogin(roll));

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
