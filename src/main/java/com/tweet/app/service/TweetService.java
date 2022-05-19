package com.tweet.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tweet.app.entity.Tweet;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.repository.TweetRepository;

import lombok.RequiredArgsConstructor;

/**
 * つぶやき業務ロジッククラス
 *
 */
@Service
@RequiredArgsConstructor
public class TweetService {

	private final TweetRepository tweetRepository;

	@Value("${error.nouser}")
	private String noUserErrMsg;

	/**
	 * 全検索
	 * 
	 * @return
	 */
	public List<Tweet> findAll() {
		return tweetRepository.findAll();
	}

	/**
	 * ユーザーIdによる検索
	 * 
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 */
	public List<Tweet> findByUserId(String userId) throws ApplicationException {
		List<Tweet> tweetList = tweetRepository.findbyUserId(userId);

		if (tweetList.isEmpty()) {
			throw new ApplicationException(noUserErrMsg);
		}

		return tweetList;
	}

	/**
	 * つぶやき作成
	 * 
	 * @param userId
	 * @param tweet
	 */
	public void createTweet(String userId, String tweet) {
		tweetRepository.Insert(userId, tweet);
	}

}
