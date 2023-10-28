package com.project.email.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.email.dto.EmailDTO;
import com.project.email.exception.UnexpectedException;
import com.project.email.model.Email;
import com.project.email.repository.EmailRepository;
import com.project.email.service.EmailService;

@Service
public class EmailServiceImplementation implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public EmailDTO sendEmail(EmailDTO emailDto) {
        try {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailDto.getToEmail());
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getBody());
        mailSender.send(mail);

        Email emailSave = new Email();
        emailSave.setToEmail(emailDto.getToEmail());
        emailSave.setSubject(emailDto.getSubject());
        emailSave.setBody(emailDto.getBody());
        emailSave.setTimeStamp(emailDto.setTimeStamp());
        emailRepository.save(emailSave);

        return emailDto; 
        } catch (Exception e) {
            throw new UnexpectedException("An exception occured");
        }
    }

    @Override
    public List<EmailDTO> getAllEmail() {
        List<Email> emails = emailRepository.findAll();
        return emails.stream().map(this::convertModelToDto).collect(Collectors.toList());
    }

    public EmailDTO convertModelToDto(Email email) {
        EmailDTO emailDto = new EmailDTO();
        emailDto.setEmailIdentity(email.getEmailIdentity());
        emailDto.setToEmail(email.getToEmail());
        emailDto.setSubject(email.getSubject());
        emailDto.setBody(email.getBody());
        emailDto.setTimeStamp(email.getTimeStamp());
        return emailDto;
    }

}
