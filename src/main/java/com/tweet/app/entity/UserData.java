package com.tweet.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import lombok.Data;

@Entity
@Data
@NamedNativeQueries({
		@NamedNativeQuery(name = "UserData.getAllUserData", query = "SELECT USERS.USERID,PASSWORD,ROLE FROM USERS "
				+ "INNER JOIN USER_ROLES ON USERS.USERID = USER_ROLES.USERID", resultClass = UserData.class),
		@NamedNativeQuery(name = "UserData.findUserData", query = "SELECT USERS.USERID,PASSWORD,ROLE FROM USERS "
				+ "INNER JOIN USER_ROLES ON USERS.USERID = USER_ROLES.USERID "
				+ "WHERE USERS.USERID = :userId AND PASSWORD = :password", resultClass = UserData.class)
})

public class UserData implements Serializable {
	private static final long serialVersionUID = 246222838200925804L;

	/**
	 * ユーザID。
	 */
	@Column(name = "USERID")
	@Id
	private String userId;

	/**
	* パスワード。
	*/
	@Column(name = "PASSWORD")
	@Id
	private String password;

	/**
	* ロール。
	*/
	@Column(name = "ROLE")
	@Id
	private String role;
}
