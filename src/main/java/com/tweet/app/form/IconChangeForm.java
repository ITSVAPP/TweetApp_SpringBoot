package com.tweet.app.form;

import org.springframework.web.multipart.MultipartFile;

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

	private MultipartFile file;
}
