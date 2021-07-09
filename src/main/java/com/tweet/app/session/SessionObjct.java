package com.tweet.app.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.tweet.app.entity.UserData;

import lombok.Data;

@Data
@Component
@SessionScope
public class SessionObjct implements Serializable {
	private static final long serialVersionUID = 8106941639208784709L;

	private UserData userData;

	public void clearUserData() {
		userData = null;
	}
}
