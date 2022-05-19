package com.tweet.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.UserDataForm;
import com.tweet.app.form.UserUpdateForm;
import com.tweet.app.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService target;

	@MockBean
	UserRepository userRepository;

	@MockBean
	PasswordEncoder passwordEncorder;

	@Value("${error.duplicatekeyerr}")
	private String duplicatekeyErrMsg;

	@Value("${error.nouser}")
	private String noUserErrMsg;

	@Test
	void findAllの正常テスト() throws Exception {
		// テストスタブ作成
		UserData rtnUser = new UserData("userId", "name", "password", "role");
		List<UserData> rtnUserList = Collections.singletonList(rtnUser);
		when(userRepository.findAll()).thenReturn(rtnUserList);

		// 実行
		List<UserData> actual = target.findAll();

		// 比較
		UserData expectedUser = new UserData("userId", "name", "password", "role");
		List<UserData> expected = Collections.singletonList(expectedUser);
		assertEquals(expected, actual);
	}

	@Test
	void findByUserIdの正常テスト() throws Exception {
		// テストスタブの作成
		UserData rtnUser = new UserData("userId", "name", "password", "role");
		when(userRepository.findByUserId("tmp")).thenReturn(rtnUser);

		// 実行
		UserData actual = target.findByUserId("tmp");

		// 比較
		UserData expected = new UserData("userId", "name", "password", "role");
		assertEquals(expected, actual);

	}

	@Test
	void findByUserIdの例外テスト() throws Exception {
		when(userRepository.findByUserId("tmp")).thenReturn(null);
		// 実行
		try {
			target.findByUserId("tmp");
		} catch (ApplicationException e) {
			String expected = noUserErrMsg;
			assertEquals(expected, e.getMessage());
		}
	}

	@Test
	void createUserの正常テスト() throws Exception {
		// テストスタブ作成
		doNothing().when(userRepository).insert("userId", "name", "encortedpassword", "role");
		when(passwordEncorder.encode("password")).thenReturn("encortedpassword");

		UserDataForm form = new UserDataForm("userId", "name", "password", "password", "role");

		// 実行
		target.createUser(form);
	}

	@Test
	void createUserの例外テスト() throws Exception {
		// テストスタブ作成
		doThrow(new DuplicateKeyException(null)).when(userRepository).insert("userId", "name", "encortedpassword",
				"role");
		when(passwordEncorder.encode("password")).thenReturn("encortedpassword");
		// 実行
		try {
			UserDataForm form = new UserDataForm("userId", "name", "password", "password", "role");
			target.createUser(form);
		} catch (ApplicationException e) {
			String expected = duplicatekeyErrMsg;
			// メッセージ確認
			assertEquals(expected, e.getMessage());
		}
	}

	@Test
	void updateUserの正常テスト() throws Exception {
		// テストスタブ作成
		doNothing().when(userRepository).update("userId", "name", "role");

		UserUpdateForm form = new UserUpdateForm("userId", "name", "role");

		// 実行
		target.updateUser(form);
	}

	@Test
	void deleteUserの正常テスト() throws Exception {
		// テストスタブ作成
		doNothing().when(userRepository).delete("userId");
		// 実行
		target.deleteUser("userId");
	}

}
