package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
	@Select("select tweetid,tweet,tweet.userId,name,date,icon_url,img_url from tweet inner join users where tweet.userid = users.userid order by date DESC")
	List<Tweet> findAll();

	/**
	 * ユーザーIdによる検索
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select tweetid,tweet,tweet.userId,name,date from tweet inner join users where tweet.userid = users.userid AND tweet.userId = #{userId} order by date DESC")
	List<Tweet> findbyUserId(String userId);

	/**
	 * 挿入
	 * 
	 * @param userId
	 * @param tweet
	 */
	@Insert("insert into tweet (userId,tweet,date) values(#{userId}, #{tweet},now())")
	void Insert(@Param("userId") String userId, @Param("tweet") String tweet);
}
