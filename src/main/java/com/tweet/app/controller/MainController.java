package com.tweet.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tweet.app.auth.CustomUserDetails;
import com.tweet.app.entity.UserData;
import com.tweet.app.form.TweetForm;
import com.tweet.app.service.TweetService;
import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final TweetService tweetService;
	private final UserService userService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("tweetList", tweetService.findAll());
		return "index";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@GetMapping("/tweetpersonal/{userId}")
	public String tweetPersonView(@PathVariable String userId, Model model) {
		UserData user = userService.findByUserId(userId);
		if (user == null) {
			model.addAttribute("nouser", true);
		} else {
			model.addAttribute("userName", user.getName());
			model.addAttribute("tweetList", tweetService.findByUserId(userId));
		}
		return "tweetpersonal";
	}

	@GetMapping("/tweetForm")
	public String showTweetForm(@ModelAttribute TweetForm form) {
		return "tweetForm";
	}

	@PostMapping("/tweetForm")
	public String tweet(@Validated TweetForm form, BindingResult bindingResult,
			@AuthenticationPrincipal CustomUserDetails user) {
		tweetService.createTweet(user.getUserId(), form.getTweet());
		return "redirect:/";
	}

}
