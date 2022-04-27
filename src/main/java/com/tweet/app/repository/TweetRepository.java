package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.Tweet;

@Mapper
public interface TweetRepository {
	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid order by date DESC")
	List<Tweet> findAll();

	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid AND tweet.userId = #{userId} order by date DESC")
	List<Tweet> findbyUserId(String userId);

	@Insert("insert into tweet (userId,tweet,date) values(#{userId}, #{tweet},now())")
	void Insert(@Param("userId") String userId, @Param("tweet") String tweet);
}
