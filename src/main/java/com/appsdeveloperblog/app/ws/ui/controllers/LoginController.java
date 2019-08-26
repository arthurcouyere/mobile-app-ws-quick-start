package com.appsdeveloperblog.app.ws.ui.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    //	Logger logger = LoggerFactory();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public ResponseEntity<String> login(@RequestHeader(value = "SSL_CLIENT_S_DN") String sslClientSubjectDN)
    {
        logger.info("SSL_CLIENT_S_DN={}", sslClientSubjectDN);
        String response = String.format("SSL_CLIENT_S_DN=%s", sslClientSubjectDN);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
