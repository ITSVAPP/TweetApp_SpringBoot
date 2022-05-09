package com.tweet.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@ControllerAdvice // @ExceptionHandler用
@Slf4j
public class ExceptionController implements ErrorController {

	@ExceptionHandler({ Throwable.class })
	public String throwableHandler(Throwable ex, Model model) {
		// エラーログの出力
		log.error(ex.getMessage());
		model.addAttribute("systemerr", true);
		return "error/error";
	}

	@RequestMapping("${server.error.path:${error.path:/error}}")
	public String myErrorHtml(HttpServletRequest request, Model model) {
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		// 404 の場合はデフォルトページへ遷移させる
		if (statusCode != null && "404".equals(statusCode.toString())) {
			log.info(request.getRequestURL() + "をリダイレクト");
			return "redirect:/";
		}

		// 403 の場合は権限エラーのメッセージを表示させる
		if (statusCode != null && "403".equals(statusCode.toString())) {
			model.addAttribute("authorityerr", true);
			return "error/error";
		}

		// 出力したい情報をセットする
		model.addAttribute("systemerr", true);
		return "error/error";
	}

}
