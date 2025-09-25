package com.dj.learning.spring.boot.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		// copied from BasicAuthenticationEntryPoint an implementation of
		// AuthenticationEntryPoint > or write your own custom logic or any condition
//		response.setHeader("WWW-Authenticate", "Basic realm=");
		response.setHeader("Dj-Spring-error-Header", "Authentication Failed for the Dj Spring app");
		response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

	}

}
