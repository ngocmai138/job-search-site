package com.asm2.mail;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.asm2.entity.User;
import com.asm2.entity.VerificationToken;
import com.asm2.service.JobService;

public class RegistrationService{
	private JavaMailSender mailSender;
	private JobService jobService;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	
	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public void sendConfirmationEmail(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken(token, user);
		jobService.saveVerificationToken(verificationToken);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getEmail());
				message.setFrom("phuthuynho138@gmail.com");
				message.setSubject("Registration Confirmation");
				
				String appUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
				String confirmUrl = appUrl+"/user/verify?token="+token;
				String emailContent = "Welcome, "+user.getUserName()+"\n\n"+
										"Thank you for registering. Please click the link below to verify your email: \n"+
										confirmUrl;
				message.setText(emailContent, false);
			}
		};
		this.mailSender.send(preparator);
	}

}
