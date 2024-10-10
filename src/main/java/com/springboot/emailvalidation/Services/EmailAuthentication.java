package com.springboot.emailvalidation.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.emailvalidation.DataModels.EmailAddress;
import com.springboot.emailvalidation.Reposotories.EmailRepo;

@Service
public class EmailAuthentication {
    @Autowired
    EmailRepo emailRepo;

    public ResponseEntity<Map<String, Object>> mailValidation(EmailAddress mailDetails) {
        System.out.println("mail:"+mailDetails.getMail());
        System.out.println("pass:"+mailDetails.getPassword());

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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
