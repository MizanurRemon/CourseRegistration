package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntityCourse;
import org.example.services.CourseService;
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
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping(UrlConstants.ADD_COURSE)
    public ResponseEntity<?> insertCourse(EntityCourse entityCourse) {

        try {
            if (entityCourse.getTitle().isEmpty() || entityCourse.getStatus().isEmpty() || entityCourse.getCredits() == 0) {
                throw new ApiRequestException("empty parameter");
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
                body.put("statusCode", HttpStatus.OK.value());


                if (courseService.insertCourse(entityCourse)) {
                    body.put("message", Constants.INSERT_SUCCESSFULLY);
                } else {
                    body.put("message", Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping(UrlConstants.COURSES)
    public ResponseEntity<?> getCourses() {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
            body.put("statusCode", HttpStatus.OK.value());
            body.put("data", courseService.getCourses());

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.UPDATE_COURSE_STATUS)
    public ResponseEntity<?> updateCourseStatus(EntityCourse entityCourse){
        try {
            if(entityCourse.getId() == 0 || entityCourse.getStatus().isEmpty()){
                throw new ApiRequestException("empty parameter");
            }else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put("statusCode", HttpStatus.OK.value());

                if (courseService.updateCourseStatus(entityCourse)){
                    body.put("message", Constants.UPDATE_SUCCESSFULLY);
                } else {
                    body.put("message", Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.COURSE_DELETE)
    public ResponseEntity<?> updateCourseStatus(int id){
        try {
            if(id == 0){
                throw new ApiRequestException("empty parameter");
            }else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put("statusCode", HttpStatus.OK.value());

                if (courseService.deleteCourse(id)){
                    body.put("message", Constants.DELETE_SUCCESSFULLY);
                } else {
                    body.put("message", Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
