package com.tweet.app.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tweet.app.entity.Tweet;
import com.tweet.app.repository.TweetRepository;

@SpringBootTest
class TweetServiceTest {

	@Autowired
	TweetService target;

	@MockBean
	TweetRepository tweetRepository;

	@Test
	void findAllの正常テスト() throws Exception {
		when(tweetRepository.findAll()).thenReturn(Collections.singletonList(new Tweet(0, null, null, null, null)));

		List<Tweet> result = target.findAll();
		assertThat(result).isEqualTo(Collections.singletonList(new Tweet(0, null, null, null, null)));
	}

	@Test
	void findByUserIdの正常テスト() throws Exception {
		when(tweetRepository.findbyUserId("tmp"))
				.thenReturn(Collections.singletonList(new Tweet(0, null, null, null, null)));

		List<Tweet> result = target.findByUserId("tmp");
		assertThat(result).isEqualTo(Collections.singletonList(new Tweet(0, null, null, null, null)));
	}

	@Test
	void createTweetの正常テスト() throws Exception {
		doNothing().when(tweetRepository).Insert("tmp", "tmp");
		target.createTweet("tmp", "tmp");
	}

}
