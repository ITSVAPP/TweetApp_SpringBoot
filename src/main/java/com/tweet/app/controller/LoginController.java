package com.tweet.app.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tweet.app.dao.TweetDAO;
import com.tweet.app.dao.UserDAO;
import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.UserData;
import com.tweet.app.session.SessionObjct;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private TweetDAO tweetDao;

	@Autowired
	private SessionObjct sessionObject;

	@RequestMapping("/login")
	public ModelAndView login(String userId, String password, ModelAndView mav, HttpSession session) {

		UserData userData = null;
		try {
			userData = userDao.find(userId, password);
		} catch (NoResultException e) {
			// ログイン画面へ遷移
			mav.setViewName("login");
			mav.addObject("message", "認証情報がことなります。");
			return mav;
		}

		sessionObject.setUserData(userData);
		List<Tweet> tweetList = tweetDao.getALL();
		mav.setViewName("tweetlist");
		mav.addObject("tweetList", tweetList);

		return mav;
	}
}
