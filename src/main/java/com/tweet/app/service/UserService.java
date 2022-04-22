package com.tweet.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweet.app.entity.UserData;
import com.tweet.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<UserData> findAll() {
		return userRepository.findAll();
	}

}
