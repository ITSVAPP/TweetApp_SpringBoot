package com.tweet.app.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * つぶやき
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

	private int tweetId;

	private String tweet;

	private String userId;

	private String name;

	private Date date;
	
	private String imgUrl;
	
	private String iconUrl;

}