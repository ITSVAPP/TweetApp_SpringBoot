package com.tweet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.tweet.app.interceptor.ControllerInterceptor;

@SpringBootApplication
public class TweetApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

	// インターセプトを登録
	@Bean
	public MappedInterceptor accessInterceptor() throws Exception {
		return new MappedInterceptor(new String[] { "/**" },
				new ControllerInterceptor());
	}
}
