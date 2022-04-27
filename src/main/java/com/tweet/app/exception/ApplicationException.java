package com.tweet.app.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ApplicationException extends Exception {

	@Getter
	@Setter
	private List<String> messageList;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
	}

}
