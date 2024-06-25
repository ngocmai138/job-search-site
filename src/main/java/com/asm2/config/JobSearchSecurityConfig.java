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
										configurer.anyRequest().permitAll())
									//	configurer.antMatchers("/").hasRole("user"))
//					.formLogin(configurer ->
//								configurer.loginProcessingUrl("/authenticateTheUser")
//											.permitAll())
//					.logout(configurer ->
//								configurer.permitAll())
//					.exceptionHandling(configurer ->
//										configurer.accessDeniedPage("/access-denied"))
					.build();
	} 
	
}
