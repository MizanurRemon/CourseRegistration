package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.helpers.Helpers;
import org.example.model.Entity.EntityCourseRegistration;
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
public class CourseRegistrationController {

    @Autowired
    CourseRegistrationService service;

    @PostMapping(UrlConstants.COURSE_REGISTRATION)
    public ResponseEntity<?> courseRegistration(EntityCourseRegistration courseRegistration) {
        try {

            if (courseRegistration.getSemester_id() == 0 || courseRegistration.getCourse_id() == 0 || courseRegistration.getStudent_id() == 0) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                courseRegistration.setStatus(Constants.PENDING);
                courseRegistration.setCreated_at(Helpers.getCurrentTime());

                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (service.courseRegistration(courseRegistration)) {
                    body.put(Constants.MESSAGE, Constants.REGISTRATION_SUCCESSFUL);
                } else {
                    body.put(Constants.MESSAGE, Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping(UrlConstants.GET_REGISTERED_COURSES)
    public ResponseEntity<?> getRegisteredCourses(){
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>();
            body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
            body.put(Constants.DATA, service.getRegisteredCourse());
            return ResponseEntity.ok(body);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
