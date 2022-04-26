package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.Tweet;

@Mapper
public interface TweetRepository {

	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid order by date DESC")
	List<Tweet> findAll();

	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid AND tweet.userId = #{userId} order by date DESC")
	List<Tweet> findbyUserId(String userId);

}
