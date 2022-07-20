package com.tweet.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tweet.app.auth.CustomUserDetails;
import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.TweetForm;
import com.tweet.app.service.TweetService;
import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * メインコントローラー
 *
 */
@Controller
@RequiredArgsConstructor
public class MainController {

	private final TweetService tweetService;
	private final UserService userService;

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

	/**
	 * 個人つぶやき一覧画面表示
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/tweetpersonal/{userId}")
	public String tweetPersonView(@PathVariable String userId, Model model) {
		UserData user;
		try {
			user = userService.findByUserId(userId);
			model.addAttribute("userName", user.getName());
			model.addAttribute("tweetList", tweetService.findByUserId(userId));

		} catch (ApplicationException e) {
			model.addAttribute("errMessage", e.getMessage());
		}
		return "tweetpersonal";
	}

	/**
	 * つぶやき画面表示
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/tweetForm")
	public String showTweetForm(@ModelAttribute TweetForm form) {
		return "tweetForm";
	}

	/**
	 * つぶやき
	 * 
	 * @param form
	 * @param bindingResult
	 * @param user
	 * @return
	 */
	@Transactional
	@PostMapping("/tweetForm")
	public String tweet(@Validated TweetForm form, BindingResult bindingResult,
			@AuthenticationPrincipal CustomUserDetails user) {

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return showTweetForm(form);
		}

		tweetService.createTweet(user.getUserId(), form);
		return "redirect:/";
	}

}
