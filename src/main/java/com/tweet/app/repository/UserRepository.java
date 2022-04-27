package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tweet.app.entity.UserData;

@Mapper
public interface UserRepository {

	@Select("select * from users where userId = #{userId}")
	UserData findByUserId(String userId);

	@Select("select * from users")
	List<UserData> findAll();

	@Insert("insert into users (userid, name, password, role) values (#{userId}, #{name}, #{password}, #{role})")
	void insert(@Param("userId") String userId,
			@Param("name") String name,
			@Param("password") String password,
			@Param("role") String role);

	@Update("update users set name=#{name}, role=#{role} where userid=#{userId}")
	void update(@Param("userId") String userId, @Param("name") String name, @Param("role") String role);

	@Delete("delete from users where userid=#{userId}")
	void delete(String userId);

}
