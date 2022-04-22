package com.tweet.app.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class BeanConfiguration {

	@Bean
	public HandlerInterceptor controllerInterceptor() throws Exception {
		return new ControllerInterceptor();
	}

}