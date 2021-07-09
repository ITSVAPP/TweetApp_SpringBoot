package com.tweet.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * The persistent class for the tweet database table.
 *
 */
@Entity
@Data
@NamedNativeQueries({
		@NamedNativeQuery(name = "Tweet.getAll", query = "SELECT NAME,TWEET,DATE "
				+ "FROM TWEET ORDER BY DATE DESC ", resultClass = Tweet.class)
})
public class Tweet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TWEET")
	private String tweet;

	public Tweet() {
	}

}