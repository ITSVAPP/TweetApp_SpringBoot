package com.tweet.app.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

public class CustomUserDetails extends User {

	@Getter
	@Setter
	private String userId;

	public CustomUserDetails(String userId, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}

	@Override
	public String toString() {
		return " [userId=" + userId + ", name=" + getUsername() + ", role=" + getAuthorities() + "]";
	}

}
