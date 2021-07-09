package com.tweet.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tweet.app.entity.UserData;

@Service
public class UserDAO implements Serializable {

	private static final long serialVersionUID = -4140660683383589524L;

	@PersistenceContext
	private EntityManager entityMnager;

	public UserDAO() {
		super();
	}

	public UserDAO(EntityManager manager) {
		super();
		this.entityMnager = manager;
	}

	public List<UserData> getALL() {
		Query query = entityMnager.createNamedQuery("UserData.getAllUserData", UserData.class);
		@SuppressWarnings("unchecked")
		List<UserData> userList = query.getResultList();
		entityMnager.close();

		return userList;
	}

	public UserData find(String userId, String password) {
		Query query = entityMnager.createNamedQuery("UserData.findUserData", UserData.class)
				.setParameter("userId", userId)
				.setParameter("password", password);
		UserData userData = (UserData) query.getSingleResult();
		entityMnager.close();

		return userData;
	}
}
