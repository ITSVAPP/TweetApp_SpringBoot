package com.tweet.app.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final HandlerInterceptor controllerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// インターセプト登録
		registry.addInterceptor(controllerInterceptor);
	}
}