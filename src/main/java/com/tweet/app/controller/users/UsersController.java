package com.tweet.app.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.UserDataForm;
import com.tweet.app.form.UserUpdateForm;
import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザーコントローラー
 * 
 * ユーザーに関する機能をまとめたコントローラー
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

	private final UserService userService;

	/**
	 * ユーザ一一覧表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String showUserList(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "users/list";
	}

	/**
	 * ユーザー作成フォーム表示
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/creationUser")
	public String showCreationForm(@ModelAttribute UserDataForm form) {
		return "users/creationForm";
	}

	/**
	 * ユーザー作成
	 * 
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@Transactional
	@PostMapping("/creationUser")
	public String createUser(@Validated UserDataForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return showCreationForm(form);
		}

		try {
			userService.createUser(form);
		} catch (ApplicationException e) {
			// 重複エラー設定
			model.addAttribute("errMessage", e.getMessage());
			return showCreationForm(form);
		}

		return "redirect:/users";
	}

	/**
	 * ユーザー削除確認画面表示
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/deleteConfirm/{userId}")
	public String showDeleteConfirmForm(@PathVariable String userId, Model model) {

		try {
			UserData user = userService.findByUserId(userId);
			model.addAttribute("user", user);
		} catch (ApplicationException e) {
			model.addAttribute("errMessage", e.getMessage());
		}
		return "users/deleteConfirm";
	}

	/**
	 * ユーザー更新画面表示
	 * 
	 * @param userId
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/updateForm/{userId}")
	public String showUpdateForm(@PathVariable String userId, @ModelAttribute UserUpdateForm form, Model model) {

		try {
			UserData user = userService.findByUserId(userId);
			model.addAttribute("user", user);
			form.setName(user.getName());
			form.setRole(user.getRole());
			form.setUserId(userId);
		} catch (ApplicationException e) {
			model.addAttribute("noUserErrMessage", e.getMessage());
		}

		return "users/updateForm";
	}

	/**
	 * ユーザー情報更新確認画面表示
	 * 
	 * @param userId
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/updateForm/{userId}")
	public String showUpdateFormComfirm(@PathVariable String userId, @Validated UserUpdateForm form,
			BindingResult bindingResult, Model model) {

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return showUpdateForm(userId, form, model);
		}

		return "users/updateFormConfirm";
	}

	/**
	 * ユーザー情報更新
	 * 
	 * @param form
	 * @return
	 */
	@Transactional
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute UserUpdateForm form) {
		userService.updateUser(form);
		return "redirect:/users";
	}

	/**
	 * ユーザー削除
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	@PostMapping("/deleteUser")
	public String updateUser(String userId) {
		userService.deleteUser(userId);
		return "redirect:/users";
	}

}
