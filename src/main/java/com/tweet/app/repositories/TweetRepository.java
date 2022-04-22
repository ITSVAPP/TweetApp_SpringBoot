package com.tweet.app.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.Tweet;

@Mapper
public interface TweetRepository {

	@Select("select tweetid,tweet,name,date from tweet inner join users where tweet.userid = users.userid order by date DESC")
	List<Tweet> findAll();
}
