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
	private String userId;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 2, max = 128)
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
