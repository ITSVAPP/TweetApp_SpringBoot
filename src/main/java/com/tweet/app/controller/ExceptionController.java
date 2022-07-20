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

/**
 * 例外コントローラー
 *
 * 例外処理の共通コントローラー
 *
 */
@Controller
@ControllerAdvice
@Slf4j
public class ExceptionController implements ErrorController {

	/**
	 * Exceptionハンドラー
	 * 
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler({ Throwable.class })
	public String throwableHandler(Throwable ex, Model model) {
		// エラーログの出力
		ex.printStackTrace();
		log.error(ex.getMessage());
		model.addAttribute("systemerr", true);
		return "error/error";
	}

	/**
	 * 独自エラー画面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("${server.error.path:${error.path:/error}}")
	public String errorHtml(HttpServletRequest request, Model model) {
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		// 404 の場合はデフォルトページへ遷移させる
		if (statusCode != null && "404".equals(statusCode.toString())) {
			log.error("404エラー");
			return "redirect:/";
		}

		// 403 の場合は権限エラーのメッセージを表示させる
		if (statusCode != null && "403".equals(statusCode.toString())) {
			log.error("権限エラー");
			model.addAttribute("authorityerr", true);
			return "error/error";
		}

		// 出力したい情報をセットする
		model.addAttribute("systemerr", true);
		return "error/error";
	}

}
