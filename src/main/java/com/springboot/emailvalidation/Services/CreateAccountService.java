package com.springboot.emailvalidation.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.emailvalidation.DataModels.EmailAddress;
import com.springboot.emailvalidation.Reposotories.EmailRepo;

@Service
public class CreateAccountService {

    @Autowired
    EmailRepo emailRepo;

    public ResponseEntity<Map<String, Object>> createAccount(EmailAddress emailDetails) {
        int rowsEffected = emailRepo.insertInEmailAddress(emailDetails.getMail(), emailDetails.getPassword());
        Map<String, Object> response = new HashMap<>();
        if(rowsEffected > 0) {
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<Map<String, Object>> updatePassword(EmailAddress emailAddress) {
        int rowsEffected = emailRepo.updateInEmailAddress(emailAddress.getMail(), emailAddress.getPassword());
        Map<String, Object> response = new HashMap<>();
        if(rowsEffected > 0) {
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }
    
}
