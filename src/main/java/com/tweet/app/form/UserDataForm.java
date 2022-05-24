package com.tweet.app.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザーフォーム
 *
 */
@Data
@AllArgsConstructor
public class UserDataForm {

	@NotBlank
	@Size(max = 20)
	private String userId;

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotBlank
	@Size(min = 2, max = 30)
	private String password;

	@NotBlank
	private String confirmPassword;

	@NotBlank
	private String role;

	@AssertTrue
	public boolean isSamePassword() {
		// Nullチェック
		if (password == null || confirmPassword == null) {
			return false;
		}
		return password.equals(confirmPassword);
	}

}
