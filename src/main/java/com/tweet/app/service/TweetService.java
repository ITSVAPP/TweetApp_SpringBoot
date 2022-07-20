package com.tweet.app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		List<String> filePathList = new ArrayList<>();
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			int count = 1;

			// ファイル出力
			if (form.getFiles() != null) {
				for (MultipartFile file : form.getFiles()) {

					String filePath = String.format("img/tweet/%s_%s_%d.%s", timestamp.getTime(), userId, count++,
							imgStorageService.getExtName(file.getOriginalFilename()));
					imgStorageService.storage(file, filePath);
					filePathList.add(filePath);
				}
			}
			String imgUrl = String.join(",", filePathList);
			tweetRepository.Insert(userId, form.getTweet(), imgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
