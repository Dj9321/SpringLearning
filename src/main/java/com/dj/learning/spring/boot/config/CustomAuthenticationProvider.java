package com.dj.learning.spring.boot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

// Creating our own custom authentication providers
// Tells spring we have our own AuthenticationProvider
@Component
@RequiredArgsConstructor
@Profile({"prod", "default"}) // when it is not Production 
public class CustomAuthenticationProvider implements AuthenticationProvider {

	// we have a custom implementation of UserDetails service:
	// UserServiceWithCustomTable implements UserDetailsService
	// we can write the logic of this service in authenticate method but spring
	// recommends to keep it separate
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	// We created one provider, default has a for loop
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString(); // getCredentials gives Object hence toString()
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(pwd, userDetails.getPassword())) {
			// write additional logic like age > 18 or other custom logic
			return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
		} else {
			// BadCredentialsException is subclass of AuthenticationException
			throw new BadCredentialsException("Invalid password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
