package com.tweet.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.tweet.app.entity.Tweet;

@MybatisTest
@Import(TweetService.class)
@TestPropertySource(locations = "/application-test.properties") // プロパティファイルの指定
class TweetServiceTest {

	@Autowired
	TweetService target;

	@Sql("/testdata/tweet/tweet01.sql")
	@Test
	void findAllの正常テスト() throws Exception {

		// 取り扱う日付の形にフォーマット設定
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		List<Tweet> expected = new ArrayList<Tweet>();
		expected.add(new Tweet(3, "これが３度目のつぶやきです。", "userId1", "name1", sdformat.parse("2020-12-06 23:01:34")));
		expected.add(new Tweet(2, "これが２度目のつぶやきです。", "userId1", "name1", sdformat.parse("2020-12-06 23:01:33")));
		expected.add(new Tweet(1, "これが１度目のつぶやきです。", "userId2", "name2", sdformat.parse("2020-12-06 23:01:32")));

		List<Tweet> actual = target.findAll();

		assertEquals(actual, expected);
	}

}
