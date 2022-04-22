package com.tweet.app.auth;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweet.app.entity.UserData;
import com.tweet.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepositry;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		UserData user = userRepositry.findByUserId(userId);

		if (user == null) {
			throw new UsernameNotFoundException("入力されたユーザ名" + userId + "がありません");
		}

		return new CustomUserDetails(user.getName(), user.getPassword(), toGrantedAuthority(user.getRole()));

	}

	private List<GrantedAuthority> toGrantedAuthority(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

}
