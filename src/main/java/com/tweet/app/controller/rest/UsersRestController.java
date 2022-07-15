package com.tweet.app.controller.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tweet.app.form.IconChangeForm;
import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/rest")
@RequiredArgsConstructor
public class UsersRestController {

	private final UserService userService;

	@PostMapping("/changeIcon")
	private String changeIcon(@RequestBody MultipartFile file, @ModelAttribute IconChangeForm form) throws IOException {
		try {
			userService.changeUserIcon(form, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}
}
