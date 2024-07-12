package mail;

import javax.servlet.http.HttpServletRequest;

import com.asm2.entity.User;

public interface RegistrationService {
	public void register(User user, HttpServletRequest request);
}
