package com.tweet.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tweet.app.entity.Tweet;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.TweetForm;
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
	private final ImgStorageService imgStorageService;

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
	 * @throws ApplicationException
	 */
	public void createTweet(String userId, TweetForm form) {
		try {

			imgStorageService.storage(form.getFiles().get(0), "img/test.jpg");
			tweetRepository.Insert(userId, form.getTweet());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
