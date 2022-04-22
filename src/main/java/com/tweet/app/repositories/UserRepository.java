package com.tweet.app.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.UserData;

@Mapper
public interface UserRepository {

	@Select("select * from users")
	List<UserData> findAll();
}
