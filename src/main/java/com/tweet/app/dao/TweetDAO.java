package com.tweet.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tweet.app.entity.Tweet;

@Service
public class TweetDAO implements Serializable {

	private static final long serialVersionUID = -4140660683383589524L;

	@PersistenceContext
	private EntityManager entityMnager;

	public TweetDAO() {
		super();
	}

	public TweetDAO(EntityManager manager) {
		super();
		this.entityMnager = manager;
	}

	public List<Tweet> getALL() {
		Query query = entityMnager.createNamedQuery("Tweet.getAll", Tweet.class);
		@SuppressWarnings("unchecked")
		List<Tweet> tweetList = query.getResultList();
		entityMnager.close();

		return tweetList;
	}

}
