package mail;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.asm2.entity.User;

public class SimpleRegistrationService implements RegistrationService{
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private UserService userService;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void register(User user, HttpServletRequest request) {
		sendConfirmationEmail(user, request);
	}
	public void sendConfirmationEmail(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		userService.createVerificationToken(user, token);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getEmail());
				message.setFrom("phuthuynho138@gmail.com");
				
				Map<String, Object> model = new HashMap<>();
				String referer = request.getHeader("Referer");
				String confirmUrl = referer+"/user/verify?token="+token;
				model.put("user", user);
				model.put("confirmationUrl", confirmUrl);
				
				VelocityContext context = new VelocityContext();
				StringWriter writer = new StringWriter();
				Template template = velocityEngine.getTemplate("com/dns/registration-confirmation.vm", "UTF-8");
				template.merge(context, writer);
				
				message.setText(writer.toString(),true);
			}
		};
		this.mailSender.send(preparator);
	}

}
