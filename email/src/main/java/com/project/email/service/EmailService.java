package com.project.email.service;

import java.util.List;

import com.project.email.dto.EmailDTO;

public interface EmailService {

    public EmailDTO sendEmail(EmailDTO emailDto);

    public List<EmailDTO> getAllEmail();
}
 