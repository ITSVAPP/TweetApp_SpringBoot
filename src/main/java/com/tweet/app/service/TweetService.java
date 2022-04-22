package com.tweet.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweet.app.entity.Tweet;
import com.tweet.app.repositories.TweetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetService {

	private final TweetRepository tweetRepository;

	public List<Tweet> findAll() {
		return tweetRepository.findAll();
	}

}
