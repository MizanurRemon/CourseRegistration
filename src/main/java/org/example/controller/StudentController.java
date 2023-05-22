package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.helpers.Helpers;
import org.example.helpers.Validation;
import org.example.model.Entity.EntityStudent;
import org.example.services.student.StudentService;
import org.example.utils.Constants;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping(UrlConstants.ADD_STUDENT)
    public ResponseEntity<?> insertStudent(EntityStudent student, MultipartFile file) {

        try {
            if (!Validation.validateImage(file) || !Validation.phoneValidation(student.getPhone()) || student.getName().isEmpty() || student.getPhone().isEmpty() || student.getRoll_no().isEmpty()) {

                if (!Validation.validateImage(file)) {
                    throw new ApiRequestException("invalid image (PNG, JPG, JPEG, WEBP)");
                } else if (!Validation.phoneValidation(student.getPhone())) {
                    throw new ApiRequestException("invalid phone (GP, Teletalk, Airtel, Robi, Banglalink)");
                } else {
                    throw new ApiRequestException(Constants.EMPTY_PARAMETER);
                }
            } else {
                student.setCreated_at(Helpers.getCurrentTime());
                student.setImage(Helpers.imageToByte(file));

                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (service.insertStudent(student)) {
                    body.put(Constants.MESSAGE, Constants.INSERT_SUCCESSFULLY);
                } else {
                    body.put(Constants.MESSAGE, Constants.FAILED);
                }
                //body.put("image", student.getImage());
                return ResponseEntity.ok(body);
            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.GET_STUDENT)
    public ResponseEntity<?> getStudent() {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>();
            body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
            body.put(Constants.DATA, service.getStudents());

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.UPDATE_STUDENT_STATUS)
    public ResponseEntity<?> updateStudentStatus(EntityStudent student) {
        try {
            if (student.getId() == 0 || student.getStatus().isEmpty()) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (service.updateStudentStatus(student)) {
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


    @PostMapping(UrlConstants.UPDATE_STUDENT_IMAGE)
    public ResponseEntity<?> updateStudentImage(EntityStudent student, MultipartFile file) {
        try {

            if (student.getId() == 0 || !Validation.validateImage(file)) {
                if (!Validation.validateImage(file)) {
                    throw new ApiRequestException("invalid image (PNG, JPG, JPEG, WEBP)");
                } else {
                    throw new ApiRequestException(Constants.EMPTY_PARAMETER);
                }
            } else {
                student.setImage(Helpers.imageToByte(file));
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (service.updateImage(student)) {
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

    @PostMapping(UrlConstants.STUDENT_INFO)
    public ResponseEntity<?> getUserByID(int id) {
        try {
            if (id != 0) {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
                body.put(Constants.DATA, service.getStudentByID(id));

                return ResponseEntity.ok(body);
            } else {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);

            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

}
