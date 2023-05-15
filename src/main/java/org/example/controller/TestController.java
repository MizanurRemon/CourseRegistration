package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.utils.UrlConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class TestController {
    @GetMapping("/")
    public ResponseEntity<?> test() {

        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
            body.put("statusCode", HttpStatus.OK.value());
            body.put("message", "Server Running");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }


    }
}
