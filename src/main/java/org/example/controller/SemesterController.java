package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.Entity.EntitySemester;
import org.example.services.semester.SemesterService;
import org.example.utils.Constants;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class SemesterController {
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


}
