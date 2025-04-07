package com.reg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${mail.username}")
	private String FROM_EMAIL;

	public void sendRegistrationEmail(String toEmail, String name) {
		String subject = "Registration Successful";
		String body = "Dear " + name + ",\n\n" + "Thank you for registering with our application!\n\n"
				+ "Feel free to explore and enjoy the features.\n\n" + "Best regards,\n" + "ReddyRani Yarraneella!";

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setFrom(FROM_EMAIL);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(body);
			javaMailSender.send(message);
			log.info("Registration email sent to {}", toEmail);
		} catch (MessagingException e) {
			log.error("Error while sending email to {}", toEmail, e);
		}
	}

}
