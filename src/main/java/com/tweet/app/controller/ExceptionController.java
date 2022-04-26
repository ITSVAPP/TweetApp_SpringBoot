package com.tweet.app.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@ControllerAdvice // @ExceptionHandler用
@Slf4j
public class ExceptionController implements ErrorController {

	/**
	 * Exception時の動作
	 * 
	 * @param ex
	 * @return mav
	 */
	@ExceptionHandler({ Throwable.class })
	public String throwableHandler(Throwable ex) {
		//スタックトレースの表示
		ex.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", ex.getMessage());
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

		// 出力したい情報をセットする
		model.addAttribute("timestamp", new Date());
		model.addAttribute("status", statusCode);
		model.addAttribute("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
		return "error/error";
	}

}
