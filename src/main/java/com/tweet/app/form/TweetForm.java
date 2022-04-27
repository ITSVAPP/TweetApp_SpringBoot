package com.tweet.app.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TweetForm {

	@NotBlank
	private String tweet;

}
