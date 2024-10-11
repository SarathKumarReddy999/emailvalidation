package com.springboot.emailvalidation.Services;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.emailvalidation.Reposotories.FlagPicsRepo;

@Service
public class FlagService {

    private static final Logger logger = LogManager.getLogger(FlagService.class);

    @Autowired
    FlagPicsRepo flagPicsRepo;

    public ResponseEntity<Map<String, Object>> sendFlag(String country) {

        logger.debug("user entered country:{}", country);
        Map<String, Object> response = new HashMap<>();
        response.put("country", country);
        byte[] flagData = flagPicsRepo.getFlagByName(country);
        String base64Flag = Base64.getEncoder().encodeToString(flagData);
        response.put("flag", base64Flag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
