package com.dj.learning.spring.boot.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		// copied from BasicAuthenticationEntryPoint an implementation of
		// AuthenticationEntryPoint > or write your own custom logic or any condition
//		response.setHeader("WWW-Authenticate", "Basic realm=");
		response.setHeader("Dj-Spring-Forbidden-Header", "Forbidden: Authorization Failed for the Dj Spring app");
		// instead of sendError we have to write sendStatus if we want our own json
//		response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		// all below code is for customized exception content (body)
		response.setStatus(HttpStatus.FORBIDDEN.value());

		LocalDateTime currentTimeStamp = LocalDateTime.now();
		String message = (accessDeniedException != null && accessDeniedException.getMessage() != null)
				? accessDeniedException.getMessage()
				: "Unauthorized message for Dj Spring app";
		String path = request.getRequestURI();

		response.setContentType("application/json:charset=UTF-8");

		String jsonResponse = String.format(
				"{\"timestamp\": \"%s\",\"status\":%d, \"error\":\"%s\",\"message\":\"%s\", \"path\": \"%s\", \"dheeraj\":\"hi\"}",
				currentTimeStamp, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), message, path);
		response.getWriter().write(jsonResponse);
	}

}
