package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntityCourse;
import org.example.services.course.CourseService;
import org.example.services.course_registration.CourseRegistrationService;
import org.example.utils.Constants;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;


@CrossOrigin("*")
@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(UrlConstants.COURSES)
    public ResponseEntity<?> getCourses() {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
            body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
            body.put(Constants.DATA, courseService.getCourses());

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
