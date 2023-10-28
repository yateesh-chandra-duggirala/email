package com.project.email.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.email.dto.EmailDTO;
import com.project.email.response.Response;
import com.project.email.response.SuccessMessages;
import com.project.email.service.EmailService;

import jakarta.validation.Valid;

@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public Response sendEmail(@Valid @RequestBody EmailDTO emailRequest){
        emailService.sendEmail(emailRequest);
        Response response = new Response(HttpStatus.OK.value(), SuccessMessages.MAIL_SENT_SUCCESS);
        return response;
    }

    @GetMapping("/view")
    public Response getAllEmail() {
        List<EmailDTO> email = emailService.getAllEmail();
        Response response = new Response(HttpStatus.OK.value(), SuccessMessages.FETCH_SUCCESS, email);
        return response;
    }
}
