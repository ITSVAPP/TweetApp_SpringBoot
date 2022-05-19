package com.tweet.app.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 業務エラー
 *
 */
@AllArgsConstructor
public class ApplicationException extends Exception {

	@Getter
	@Setter
	private List<String> messageList;

	/**
	 * デフォルトコンストラクタ
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

}
