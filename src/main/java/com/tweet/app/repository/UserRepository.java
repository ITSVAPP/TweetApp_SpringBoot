package com.tweet.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tweet.app.entity.UserData;

/**
 * ユーザーリポジトリ
 *
 */
@Mapper
public interface UserRepository {

	/**
	 * ユーザーIdによる検索
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from users where userId = #{userId}")
	UserData findByUserId(String userId);

	/**
	 * 全検索
	 * 
	 * @return
	 */
	@Select("select * from users")
	List<UserData> findAll();

	/**
	 * 挿入
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @param role
	 */
	@Insert("insert into users (userid, name, password, role) values (#{userId}, #{name}, #{password}, #{role})")
	void insert(@Param("userId") String userId, @Param("name") String name, @Param("password") String password,
			@Param("role") String role);

	/**
	 * 更新
	 * 
	 * @param userId
	 * @param name
	 * @param role
	 */
	@Update("update users set name=#{name}, role=#{role} where userid=#{userId}")
	void update(@Param("userId") String userId, @Param("name") String name, @Param("role") String role);

	/**
	 * 削除
	 * 
	 * @param userId
	 */
	@Delete("delete from users where userid=#{userId}")
	void delete(String userId);

	/**
	 * アイコン変更
	 * 
	 * 
	 */
	@Update("update users set icon_url=#{iconUrl} where userid=#{userId}")
	void changeIcon(@Param("userId") String userId, @Param("iconUrl") String iconUrl);

}
