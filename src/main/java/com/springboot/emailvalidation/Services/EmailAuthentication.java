package com.springboot.emailvalidation.Services;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.emailvalidation.DataModels.EmailAddress;
import com.springboot.emailvalidation.Reposotories.EmailRepo;

@Service
public class EmailAuthentication {

    private static final Logger logger = LogManager.getLogger(EmailAuthentication.class);

    @Autowired
    EmailRepo emailRepo;

    public ResponseEntity<Map<String, Object>> mailValidation(EmailAddress mailDetails) {
        logger.debug("User details are mail:{} password:{}", mailDetails.getMail(), mailDetails.getPassword());

        EmailAddress model = emailRepo.getBymail(mailDetails.getMail());
        Map<String, Object> response = new HashMap<>();
        if(model == null) {
            response.put("email", mailDetails.getMail());
            response.put("allow", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(model.getMail().equalsIgnoreCase(mailDetails.getMail()) && model.getPassword().equalsIgnoreCase(mailDetails.getPassword())) {
            response.put("allow", true);
        } else {
            response.put("allow", false);
        }
        response.put("email", mailDetails.getMail());
        logger.debug("Response status mail:{} allow:{}", mailDetails.getMail(), response.get("allow"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
