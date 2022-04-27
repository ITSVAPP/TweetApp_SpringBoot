package com.tweet.app.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateForm {

	@NotBlank
	private String userId;

	@NotBlank
	private String name;

	@NotBlank
	private String role;

}
