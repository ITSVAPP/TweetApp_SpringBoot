package com.tweet.app.form;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザーフォーム
 *
 */
@Data
@AllArgsConstructor
public class IconChangeForm {

	private String userId;

	private String imageType;
}
