package com.tweet.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// コントローラー実行前に行いたい処理を記述する
		// 例えば、リクエストを受け付けたログの書き出し など

		if (handler instanceof ResourceHttpRequestHandler) {
			// staticファイルはインターセプトさせずにスルーさせる
			return true;
		}

		// TODO 後でログにする
		System.out.print("requestURL:");
		System.out.println(request.getRequestURL());

		return true;
	}
}
