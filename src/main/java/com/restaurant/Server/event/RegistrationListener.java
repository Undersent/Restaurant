package com.restaurant.Server.event;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final StaffService service;

    private final MessageSource messages;

    private final JavaMailSender mailSender;

    private final Environment env;

    @Autowired
    public RegistrationListener(StaffService service, MessageSource messages, JavaMailSender mailSender, Environment env) {
        this.service = service;
        this.messages = messages;
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final Staff user = event.getStaff();
        final String token = UUID.randomUUID().toString();

        final SimpleMailMessage email = constructEmailMessage(event, user);
        mailSender.send(email);
    }


    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final Staff staff) {
        final String recipientAddress = staff.getEmail();
        final String subject = "Registration Confirmation";
        //final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        //final String message = messages.getMessage("message.regSucc", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("You registered successfully. We will send you a confirmation message to your email account."
                + " \r\n" + "your password "
                + staff.getPassword());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
