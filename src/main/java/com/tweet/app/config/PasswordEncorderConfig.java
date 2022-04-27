package com.tweet.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class PasswordEncorderConfig {

	@Bean
	public PasswordEncoder passwordEncorder() {
		return new Pbkdf2PasswordEncoder();
	}

}