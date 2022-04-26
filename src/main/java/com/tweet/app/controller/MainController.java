package com.tweet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tweet.app.service.TweetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final TweetService tweetService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("tweetList", tweetService.findAll());
		return "index";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@GetMapping("/tweetpersonal")
	public String tweetPersonView(String userId, Model model) {
		model.addAttribute("tweetList", tweetService.findByUserId(userId));
		return "tweetpersonal";
	}
}
