package com.tweet.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * コントローラーインターセプタークラス
 *
 */
@Component
@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {

	/**
	 * 前処理ハンドラー
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// コントローラー実行前に行いたい処理を記述する

		if (handler instanceof ResourceHttpRequestHandler) {
			// staticファイルはインターセプトさせずにスルーさせる
			return true;
		}

		String role = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		log.info("URL:" + request.getRequestURL());
		log.info("User: " + role);

		return true;
	}
}
