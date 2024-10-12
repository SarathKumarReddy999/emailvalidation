package com.springboot.emailvalidation.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.emailvalidation.DataModels.EmailAddress;
import com.springboot.emailvalidation.Services.CreateAccountService;
import com.springboot.emailvalidation.Services.EmailAuthentication;
import com.springboot.emailvalidation.Services.FlagService;

@RestController
public class AppController {

    @Autowired
    EmailAuthentication emailAuthentication;

    @Autowired
    CreateAccountService createAccountService;

    @Autowired
    FlagService flagService;

    @CrossOrigin(origins = "*")
    @PostMapping("/api/v1/createAccount")
    public ResponseEntity<Map<String, Object>> createAccountForUser(@RequestBody EmailAddress emailAddress) {
        return createAccountService.createAccount(emailAddress);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/v1/changePassword")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody EmailAddress emailAddress) {
        return createAccountService.updatePassword(emailAddress);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/v1/validateEmail")
    public ResponseEntity<Map<String, Object>> validateEmail(@RequestBody EmailAddress emailAddress) {

        return emailAuthentication.mailValidation(emailAddress);
        
    }

    //sends flag in base64 encoded format
    @CrossOrigin(origins = "*")
    @GetMapping("/api/v1/getCountryFlag/{Country}")
    public ResponseEntity<Map<String, Object>> sendFlag(@PathVariable String Country) {

        return flagService.sendFlag(Country);
        
    }

    
}
