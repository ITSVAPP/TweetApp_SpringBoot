package com.tweet.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
		List<Tweet> rtnTweetList = new ArrayList<Tweet>();
		rtnTweetList.add(new Tweet(1, "tweet1", "user1", "username1", null));
		rtnTweetList.add(new Tweet(2, "tweet2", "user2", "username2", null));

		List<Tweet> expected = new ArrayList<Tweet>();
		expected.add(new Tweet(1, "tweet1", "user1", "username1", null));
		expected.add(new Tweet(2, "tweet2", "user2", "username2", null));

		when(tweetRepository.findAll()).thenReturn(rtnTweetList);

		List<Tweet> actual = target.findAll();

		assertEquals(actual, expected);
	}

}
