package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.Tweet;

/**
 * つぶやきリポジトリ
 *
 */
@Mapper
public interface TweetRepository {
	/**
	 * 全検索
	 */
	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid order by date DESC")
	List<Tweet> findAll();
}
