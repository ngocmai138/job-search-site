package com.asm2.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class JobAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String redirectUrl = determineTargetUrl(authentication);
		redirectUrl += "?success=true";
		if(response.isCommitted()) {
			return;
		}
		getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		boolean isAdmin = false;
		for(GrantedAuthority authority: authentication.getAuthorities()) {
			if(authority.getAuthority().equals("ROLE_admin")) {
				isAdmin = true;
				break;
			}
		}
		if(isAdmin) {
			return "/admin";
		}else {
			return "/";
		}
	}

}
