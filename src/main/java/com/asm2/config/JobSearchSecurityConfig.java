package com.asm2.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableWebSecurity
public class JobSearchSecurityConfig {
	private DataSource securityDataSource;
	private JobAuthenticationSuccessHandler authenticationSuccessHandler;
	private Logger logger = LoggerFactory.getLogger(JobSearchSecurityConfig.class);

	@Autowired
	public JobSearchSecurityConfig(DataSource securityDataSource, JobAuthenticationSuccessHandler authenticationSuccessHandler) {
		super();
		this.securityDataSource = securityDataSource;
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	@Bean
	public UserDetailsManager userDetailsService() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new  JdbcUserDetailsManager(securityDataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery(""
				+ "select user_name, password, isActive as enabled from user where user_name=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(""
				+ "select u.user_name, r.role_name as authority "
				+ "from authorities a "
				+ "join user u on a.user_id = u.id "
				+ "join role r on a.role_id = r.id "
				+ "where u.user_name=?");
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
					.authorizeRequests(configurer ->
										configurer.antMatchers("/user/uploadCv").permitAll()
													.antMatchers("/**").permitAll())
					.authorizeRequests(configurer ->
										configurer.antMatchers("/admin").hasRole("admin")
													.antMatchers("/**").permitAll())
					.formLogin(configurer ->
										configurer.loginPage("/login")
													.loginProcessingUrl("/authenticateTheUser")
													.successHandler(authenticationSuccessHandler)
													.failureUrl("/showLoginPage?error=true")
													.permitAll())
					.logout(configurer ->
								configurer.logoutUrl("/logout")
											.logoutSuccessUrl("/?logout")
											.permitAll())
					.exceptionHandling(configurer ->
										configurer.accessDeniedPage("/access-denied"))
					.csrf(csrf->csrf.disable())
					.build();
	} 
	
	
	
}
