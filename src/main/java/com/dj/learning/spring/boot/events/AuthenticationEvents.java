package com.dj.learning.spring.boot.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to check events for Authentication successful or failure.
 */
@Component
@Slf4j
public class AuthenticationEvents {

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent successEvent) {
		log.info("Login successful or write code to send email: {}", successEvent.getAuthentication().getName());
	}

	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
		log.error("Login failed for user: {} due to {}", failureEvent.getAuthentication().getName(),
				failureEvent.getException().getMessage());
	}

}
