package com.tweet.app.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 認証失敗用Handlerクラス
 * 
 * 認証失敗用HandlerSecurityConfigで failureHandler(new AuthFailure()) を設定した場合動作する
 */
public class AuthFailure implements AuthenticationFailureHandler {

	/**
	 * 認証失敗用Handler
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String queryParams = request.getQueryString() == null ? "" : "?" + request.getQueryString();

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/login?error" + queryParams);

	}

}
