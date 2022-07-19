package com.tweet.app.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.IconChangeForm;
import com.tweet.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/rest")
@RequiredArgsConstructor
public class UsersRestController {

	private final UserService userService;

	@PostMapping("/changeIcon")
	private String changeIcon(@RequestBody MultipartFile file, @ModelAttribute IconChangeForm form) {
		try {
			userService.changeUserIcon(form, file);
		} catch (ApplicationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return "true";
	}
}
