package com.asm2.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.asm2.mail.RegistrationService;
import com.asm2.service.JobService;

@Configuration
public class MailConfiguration {
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("phuthuynho138@gmail.com");
		mailSender.setPassword("rjnc gnra pjlz xmzz");
		
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.debug", "true");
		return mailSender;
	}
	@Bean
	public RegistrationService registrationService(JobService jobService) {
		RegistrationService registrationService = new RegistrationService();
		registrationService.setMailSender(mailSender());
		registrationService.setJobService(jobService);
		return registrationService;
	}
}
