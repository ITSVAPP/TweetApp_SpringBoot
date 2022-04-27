package com.tweet.app.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweet.app.entity.UserData;
import com.tweet.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncorder;

	public List<UserData> findAll() {
		return userRepository.findAll();
	}

	public void createUser(String userId, String name, String passowrd, String roll) {
		userRepository.insert(userId, name, passwordEncorder.encode(passowrd), roll);
	}

	public UserData findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	public void updateUser(String userId, String name, String role) {
		userRepository.update(userId, name, role);
	}

	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}

}
