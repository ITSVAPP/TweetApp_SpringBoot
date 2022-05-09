package com.tweet.app.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
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

	public void createUser(String userId, String name, String passowrd, String roll) throws ApplicationException {

		try {
			userRepository.insert(userId, name, passwordEncorder.encode(passowrd), roll);
		} catch (DuplicateKeyException e) {
			throw new ApplicationException("duplicatekeyerr");
		}
	}

	public UserData findByUserId(String userId) throws ApplicationException {
		UserData userData = userRepository.findByUserId(userId);

		if (userData == null) {
			throw new ApplicationException("nouser");
		}

		return userData;
	}

	public void updateUser(String userId, String name, String role) {
		userRepository.update(userId, name, role);
	}

	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}

}
