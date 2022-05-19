package com.tweet.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * パスワードエンコーダー設定クラス
 * 
 * パスワードを暗号化する
 */
@Configuration
public class PasswordEncorderConfig {

	/**
	 * パスワードエンコーダー
	 */
	@Bean
	public PasswordEncoder passwordEncorder() {
		return new Pbkdf2PasswordEncoder();
	}

}
