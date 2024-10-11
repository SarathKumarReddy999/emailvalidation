package com.springboot.emailvalidation.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.emailvalidation.DataModels.EmailAddress;
import com.springboot.emailvalidation.Reposotories.EmailRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CreateAccountService {

    private static final Logger logger = LogManager.getLogger(CreateAccountService.class);

    @Autowired
    EmailRepo emailRepo;

    public ResponseEntity<Map<String, Object>> createAccount(EmailAddress emailDetails) {
        int rowsEffected = emailRepo.insertInEmailAddress(emailDetails.getMail(), emailDetails.getPassword());
        Map<String, Object> response = new HashMap<>();
        if(rowsEffected > 0) {
            response.put("status", "success");
            logger.debug("Account created successful mail:{}", emailDetails.getMail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failed");
            logger.debug("Account creation failed mail:{}", emailDetails.getMail());
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<Map<String, Object>> updatePassword(EmailAddress emailAddress) {
        int rowsEffected = emailRepo.updateInEmailAddress(emailAddress.getMail(), emailAddress.getPassword());
        Map<String, Object> response = new HashMap<>();
        if(rowsEffected > 0) {
            response.put("status", "success");
            logger.debug("Password updated successfully mail:{}", emailAddress.getMail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failed");
            logger.debug("Password updation failed mail:{}", emailAddress.getMail());
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }
    
}
