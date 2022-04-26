package com.tweet.app.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncorder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// 静的ファイルの除外
				.mvcMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/favicon.ico")
				.permitAll()
				// ログイン画面の除外
				.mvcMatchers("/login/**").permitAll()
				.mvcMatchers("/users/**").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncorder);
	}

}
