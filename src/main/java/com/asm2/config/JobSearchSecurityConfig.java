package com.asm2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JobSearchSecurityConfig {
	private DataSource securityDataSource;

	@Autowired
	public JobSearchSecurityConfig(DataSource securityDataSource) {
		super();
		this.securityDataSource = securityDataSource;
	}
	
	@Bean
	public UserDetailsManager userDetailsService() {
		return new JdbcUserDetailsManager(securityDataSource);
	}
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
					.authorizeRequests(configurer ->
										configurer.antMatchers("/").hasRole("User"))
					.formLogin(configurer ->
								configurer.loginProcessingUrl("authenticateTheUser")
											.permitAll())
					.logout(configurer ->
								configurer.permitAll())
					.exceptionHandling(configurer ->
										configurer.accessDeniedPage("/access-denied"))
					.build();
	} 
	
}
