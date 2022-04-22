package com.tweet.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
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

		System.out.println("前処理");
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());

		// 以下は、特定のコントローラーメソッドに実行したい場合に利用する
		if (AnnotationUtils.findAnnotation(
				((HandlerMethod) handler).getMethod(),
				LoginCheckAnnotaion.class) != null) {
			System.out.println("ログインチェックされました");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {

		if (handler instanceof ResourceHttpRequestHandler) {
			// staticファイルはインターセプトさせずにスルーさせる
			return;
		}

		// コントローラーでの処理が完了し、ビューのレンダリング前に行いたい処理を記述する
		System.out.println("中処理");

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

		if (handler instanceof ResourceHttpRequestHandler) {
			// staticファイルはインターセプトさせずにスルーさせる
			return;
		}

		System.out.println("後処理");
	}
}
