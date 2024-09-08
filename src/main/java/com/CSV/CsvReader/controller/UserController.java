package com.CSV.CsvReader.controller;

import com.CSV.CsvReader.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/validate")
    public ResponseEntity<String> validateJson(@RequestBody String jsonString) {
        try {
            userValidator.validate(jsonString);
            return ResponseEntity.ok("JSON is valid");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }
}
