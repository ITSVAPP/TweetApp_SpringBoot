package com.tweet.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tweet.app.dao.TweetDAO;
import com.tweet.app.entity.Tweet;
import com.tweet.app.session.SessionObjct;

import interceptor.LoginCheckAnnotaion;

@Controller
public class TweetListController {

	@Autowired
	private TweetDAO tweetDao;

	@Autowired
	private SessionObjct sessionObject;

	@RequestMapping("/tweetlist")
	@LoginCheckAnnotaion
	public ModelAndView tweetListView(String name, ModelAndView mav) {
		List<Tweet> tweetList = tweetDao.getALL();
		mav.setViewName("tweetlist");
		mav.addObject("tweetList", tweetList);

		System.out.println(sessionObject.getUserData().getUserId());
		return mav;
	}
}
