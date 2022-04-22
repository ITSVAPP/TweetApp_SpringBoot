package com.tweet.app.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice // @ExceptionHandlerを私用するためのもの
public class ExceptionController implements ErrorController {

	/**
	 * エラー時の動作
	 * 
	 * @param ex
	 * @return mav
	 */
	@ExceptionHandler({ Throwable.class })
	public String throwableHandler(Throwable ex) {
		// TODOログにしてもいい
		System.out.println("エクセプション時");
		//スタックトレースの表示
		ex.printStackTrace();

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", ex.getMessage());
		return "error/error";
	}

	@RequestMapping("${server.error.path:${error.path:/error}}") // エラーページへのマッピング
	public ModelAndView myErrorHtml(HttpServletRequest request) {

		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		ModelAndView mav = null;

		// 404 の場合はデフォルトページへ遷移させる
		if (statusCode != null && "404".equals(statusCode.toString())) {
			mav = new ModelAndView("redirect:/");
			System.out.println("リダイレクトされました");
			return mav;
		}

		// 出力したい情報をセットする
		mav = new ModelAndView();
		mav.setStatus(status); // HTTP ステータスをセットする
		mav.setViewName("error/error"); // error.html
		mav.addObject("timestamp", new Date());
		mav.addObject("status", status.value());
		mav.addObject("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
		return mav;
	}

}
