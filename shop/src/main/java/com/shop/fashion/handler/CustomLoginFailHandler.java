package com.shop.fashion.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage;
		
		// 아이디가 존재하지만 비밀번호가 맞지 않을때
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "match";
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "exist";
		} else {
			errorMessage = "etc";
		}
		
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		response.sendRedirect("/security/login_form?error=true&exception=" + errorMessage);
	}

}
