package com.tweet.app.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tweet {

	private int tweetId;

	private String tweet;

	private String userId;

	private String name;

	private Date date;

}