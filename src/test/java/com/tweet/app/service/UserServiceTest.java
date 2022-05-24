package com.tweet.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.UserDataForm;
import com.tweet.app.form.UserUpdateForm;

@MybatisTest
@Import(UserService.class) // MybatisTest使用時にtargetが依存注入されないため手動でインポート
@TestPropertySource(locations = "/application-test.properties") // プロパティファイルの指定
class UserServiceTest {
	@Autowired
	UserService target;

	@MockBean
	PasswordEncoder passwordEncorder;

	@Value("${error.duplicatekeyerr}")
	private String duplicatekeyErrMsg;

	@Value("${error.nouser}")
	private String noUserErrMsg;

	@Sql("/testdata/users/user01.sql")
	@Test
	void findAllの正常テスト() throws Exception {
		// 期待
		List<UserData> expected = new ArrayList<UserData>();
		expected.add(new UserData("userId1", "name1", "password1", "role1"));
		expected.add(new UserData("userId2", "name2", "password2", "role2"));

		// 実行
		List<UserData> actual = target.findAll();

		// 比較
		assertEquals(expected, actual);
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void findByUserIdの正常テスト() throws Exception {

		// 期待
		UserData expected = new UserData("userId1", "name1", "password1", "role1");

		// 実行
		UserData actual = target.findByUserId("userId1");

		assertEquals(expected, actual);
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void findByUserIdの例外テスト() throws Exception {
		// 実行
		try {
			target.findByUserId("tmp");
		} catch (ApplicationException e) {
			String expected = noUserErrMsg;
			assertEquals(expected, e.getMessage());
		}
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void createUserの正常テスト() throws Exception {
		// テストスタブ作成
		when(passwordEncorder.encode("password")).thenReturn("encortedpassword");

		UserDataForm form = new UserDataForm("userId", "name", "password", "password", "role");

		// 実行
		target.createUser(form);
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void createUserの例外テスト() throws Exception {
		// テストスタブ作成
		when(passwordEncorder.encode("password")).thenReturn("encortedpassword");
		// 実行
		try {
			UserDataForm form = new UserDataForm("userId1", "name", "password", "password", "role");
			target.createUser(form);
		} catch (ApplicationException e) {
			String expected = duplicatekeyErrMsg;
			// メッセージ確認
			assertEquals(expected, e.getMessage());
		}
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void updateUserの正常テスト() throws Exception {

		UserUpdateForm form = new UserUpdateForm("userId1", "name", "role");

		// 実行
		target.updateUser(form);
	}

	@Sql("/testdata/users/user01.sql")
	@Test
	void deleteUserの正常テスト() throws Exception {
		// 実行
		target.deleteUser("userId1");
	}

}
