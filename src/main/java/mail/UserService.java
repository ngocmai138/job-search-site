package mail;

import java.util.HashMap;
import java.util.Map;

import com.asm2.entity.User;
import com.asm2.service.JobService;

public class UserService {
	private JobService jobService;
	private Map<String, String> verificationTokens = new HashMap<>();
	public User findByEmail(String email) {
		return jobService.getUserByEmail(email);
	}
	public void createVerificationToken(User user, String token) {
		verificationTokens.put(token, user.getEmail());
	}
	
	public User getUserByVerificationToken(String token) {
		String email = verificationTokens.get(token);
		return findByEmail(email);
	}
	public void confirmUser(User user) {
		user.setStatus(1);
		jobService.addOrUpdateUser(user);
	}
}
