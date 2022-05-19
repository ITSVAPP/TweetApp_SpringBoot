package com.tweet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * つぶやきアプリ
 *
 */
@SpringBootApplication
public class TweetApplication extends SpringBootServletInitializer {

	/**
	 * メインメソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

}
