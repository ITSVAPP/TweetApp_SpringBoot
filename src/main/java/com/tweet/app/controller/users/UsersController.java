package com.tweet.app.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

	private final UserService userService;

	@GetMapping
	public String showUserList(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "users/list";
	}
}
