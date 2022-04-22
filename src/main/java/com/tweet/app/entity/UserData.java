package com.tweet.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {

	private String userId;

	private String name;

	private String password;

	private String role;
}
