package org.example.AdminController;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntitySemester;
import org.example.services.semester.SemesterService;
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
public class AdminSemesterController {
    @Autowired
    SemesterService semesterService;

    @GetMapping(UrlConstants.GET_SEMESTER)
    public ResponseEntity<?> getSemester() {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>();
            body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
            body.put(Constants.DATA, semesterService.getSemester());

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.ADD_SEMESTER)
    public ResponseEntity<?> insertSemester(EntitySemester semester) {
        try {
            if (semester.getTitle().isEmpty() || semester.getStatus().isEmpty()) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
                if (semesterService.insertSemester(semester)) {
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


    @PostMapping(UrlConstants.UPDATE_SEMESTER_STATUS)
    public ResponseEntity<?> updateSemesterStatus(EntitySemester semester) {

        try {
            if (semester.getId() == 0 || semester.getStatus().isEmpty()) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());
                if (semesterService.updateSemesterStatus(semester)) {
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

    @PostMapping(UrlConstants.SEMESTER_DELETE)
    public ResponseEntity<?> deleteSemester(int id) {
        try {
            if (id == 0) {
                throw new ApiRequestException(Constants.EMPTY_PARAMETER);
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>();
                body.put(Constants.STATUS_CODE, HttpStatus.OK.value());

                if (semesterService.deleteSemester(id)) {
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
}
