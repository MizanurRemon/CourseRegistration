package org.example.controller;


import org.example.utils.UrlConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(UrlConstants.V2)
public class AdminTestController {
    @PostMapping("/")
    public ResponseEntity<?> test(){
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("statusCode", HttpStatus.OK.value());
        body.put("message", "test");

        return ResponseEntity.ok(body);
    }
}
