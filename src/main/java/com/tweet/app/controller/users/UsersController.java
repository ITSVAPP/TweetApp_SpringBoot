package com.tweet.app.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tweet.app.form.UserDataForm;
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

	@GetMapping("/creationForm")
	public String showCreationForm(@ModelAttribute UserDataForm form) {
		return "users/creationForm";
	}

	@PostMapping("/creatUser")
	public String createUser(@Validated UserDataForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return showCreationForm(form);
		}
		userService.createUser(form.getUserId(), form.getName(), form.getPassword(), form.getRole());

		return "redirect:/users";
	}

}
