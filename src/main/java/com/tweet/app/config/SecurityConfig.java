package com.tweet.app.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

/**
 * セキュリティー設定クラス
 * 
 * SpringSecurityの設定を管理するクラス
 *
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncorder;

	/**
	 * 設定
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 認証不要の設定
		http.authorizeRequests()
				// 静的ファイルの除外
				.mvcMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll();

		// 認証設定
		http.authorizeRequests()
				// ログイン画面
				.mvcMatchers("/login/**").permitAll()
				// 管理者ユーザのログイン箇所
				.mvcMatchers("/users/**").hasAnyAuthority("ADMIN").anyRequest().authenticated();

		// ログイン画面
		http.formLogin().loginPage("/login").permitAll().usernameParameter("userId") // ログインページのユーザーID
				.passwordParameter("password");// ログインページのパスワード
	}

	/**
	 * パスワードのエンコーディング設定
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncorder);
	}

}
