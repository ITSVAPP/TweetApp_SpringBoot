package com.tweet.app.controller.users;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tweet.app.entity.UserData;
import com.tweet.app.form.UserDataForm;
import com.tweet.app.form.UserUpdateForm;
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

	@GetMapping("/creationUser")
	public String showCreationForm(@ModelAttribute UserDataForm form) {
		return "users/creationForm";
	}

	@PostMapping("/creationUser")
	public String createUser(@Validated UserDataForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return showCreationForm(form);
		}

		try {
			userService.createUser(form.getUserId(), form.getName(), form.getPassword(), form.getRole());
		} catch (DuplicateKeyException e) {
			// 重複エラー設定
			model.addAttribute("duplicatekeyerr", true);
			return showCreationForm(form);
		}

		return "redirect:/users";
	}

	@GetMapping("/deleteConfirm/{userId}")
	public String showDeleteConfirmForm(@PathVariable String userId, Model model) {
		UserData user = userService.findByUserId(userId);
		if (user == null) {
			model.addAttribute("nouser", true);
		} else {
			model.addAttribute("user", user);
		}
		return "users/deleteConfirm";
	}

	@GetMapping("/updateForm/{userId}")
	public String showUpdateForm(@PathVariable String userId, @ModelAttribute UserUpdateForm form, Model model) {
		UserData user = userService.findByUserId(userId);
		if (user == null) {
			model.addAttribute("nouser", true);
		} else {
			form.setName(user.getName());
			form.setRole(user.getRole());
			form.setUserId(userId);
		}
		return "users/updateForm";
	}

	@PostMapping("/updateForm/{userId}")
	public String showUpdateFormComfirm(@PathVariable String userId, @Validated UserUpdateForm form,
			BindingResult bindingResult, Model model) {

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return showUpdateForm(userId, form, model);
		}

		return "users/updateFormConfirm";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute UserUpdateForm form) {
		userService.updateUser(form.getUserId(), form.getName(), form.getRole());
		return "redirect:/users";
	}

	@PostMapping("/deleteUser")
	public String updateUser(String userId) {
		userService.deleteUser(userId);
		return "redirect:/users";
	}

}
