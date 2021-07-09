package com.tweet.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TweetPersonListController {

	@RequestMapping("/tweetpersonal")
	public ModelAndView tweetPersonListView(String name, ModelAndView mav, HttpSession session) {
		mav.setViewName("tweetpersonlist");
		return mav;
	}
}
