package com.project.email.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.email.response.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    private Long emailIdentity;

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    private String toEmail;

    private String subject;

    @NotBlank(message = ValidationMessages.EMAIL_BODY_REQUIRED)
    private String body;

    private String timeStamp;

    public String setTimeStamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return currentDateTime.format(formatter);
    }
}
