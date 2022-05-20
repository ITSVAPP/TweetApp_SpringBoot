package com.tweet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tweet.app.service.TweetService;

import lombok.RequiredArgsConstructor;

/**
 * メインコントローラー
 *
 */
@Controller
@RequiredArgsConstructor
public class MainController {

	private final TweetService tweetService;

	/**
	 * ホーム
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("tweetList", tweetService.findAll());
		return "index";
	}

	/**
	 * ログイン画面の表示
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

}
