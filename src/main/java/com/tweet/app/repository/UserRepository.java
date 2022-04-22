package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tweet.app.entity.UserData;

@Mapper
public interface UserRepository {

	@Select("select * from users where userId = #{userId}")
	public UserData findByUserId(String userId);

	@Select("select * from users")
	public List<UserData> findAll();

	@Insert("insert into users (userid, name, password, role) values (#{userId}, #{name}, #{password}, #{role})")
	public void insert(@Param("userId") String userId,
			@Param("name") String name,
			@Param("password") String password,
			@Param("role") String role);

}
