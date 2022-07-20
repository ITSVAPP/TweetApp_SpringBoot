package com.tweet.app.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * つぶやきフォーム
 *
 */
@Data
@AllArgsConstructor
public class TweetForm {

	@NotBlank
	private String tweet;

	private List<MultipartFile> files;

}
