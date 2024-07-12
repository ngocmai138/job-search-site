package com.asm2.config;

import java.util.Properties;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import mail.SimpleRegistrationService;
import mail.UserService;

@Configuration
public class MailConfiguration {
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail.asm2.com");
		return mailSender;
	}
	@Bean
	public VelocityEngineFactoryBean velocityEngine() {
		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
		Properties prop = new Properties();
		prop.put("resource.loader", "class");
		prop.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.setVelocityProperties(prop);
		return velocityEngine;
	}
	@Bean
	public SimpleRegistrationService registrationService(UserService userService) {
		SimpleRegistrationService registrationService = new SimpleRegistrationService();
		registrationService.setMailSender(mailSender());
		registrationService.setVelocityEngine(velocityEngine().getObject());
		registrationService.setUserService(userService);
		return registrationService;
	}
}
