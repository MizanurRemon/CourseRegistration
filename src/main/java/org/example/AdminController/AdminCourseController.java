package org.example.AdminController;

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


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(UrlConstants.V2)
public class AdminCourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseRegistrationService service;

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

    @PostMapping(UrlConstants.ADD_COURSE)
    public ResponseEntity<?> insertCourse(EntityCourse entityCourse) {

        try {
            if (entityCourse.getTitle().isEmpty() || entityCourse.getStatus().isEmpty() || entityCourse.getCredits() == 0) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());


                if (courseService.insertCourse(entityCourse)) {
                    body.put(Constants.MESSAGE, Constants.INSERT_SUCCESSFULLY);
                } else {
                    body.put(Constants.MESSAGE, Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.UPDATE_COURSE_STATUS)
    public ResponseEntity<?> updateCourseStatus(EntityCourse entityCourse) {
        try {
            if (entityCourse.getId() == 0 || entityCourse.getStatus().isEmpty()) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (courseService.updateCourseStatus(entityCourse)) {
                    body.put(Constants.MESSAGE, Constants.UPDATE_SUCCESSFULLY);
                } else {
                    body.put(Constants.MESSAGE, Constants.FAILED);
                }

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.COURSE_DELETE)
    public ResponseEntity<?> updateCourseStatus(int id) {
        try {
            if (id == 0) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (courseService.deleteCourse(id)) {
                    body.put(Constants.MESSAGE, Constants.DELETE_SUCCESSFULLY);
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
