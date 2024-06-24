package com.asm2.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.asm2")
@PropertySource("classpath:persistence-mysql.properties")
public class JobSearchConfig {
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger(getClass().getName());
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
	public DataSource SecurityDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialPoolSize(getIntProperty(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMinPoolSize(getIntProperty(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxPoolSize(getIntProperty(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMaxIdleTime(getIntProperty(env.getProperty("connection.pool.maxIdleTime")));
		return dataSource;
	}
	
	private int getIntProperty(String proName) {
		String proVal = env.getProperty(proName);
		int proInt = Integer.parseInt(proVal);
		return proInt;
	}
}
