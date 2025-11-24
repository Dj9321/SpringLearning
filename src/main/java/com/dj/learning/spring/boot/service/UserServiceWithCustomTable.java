package com.dj.learning.spring.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dj.learning.spring.boot.entity.Customer;
import com.dj.learning.spring.boot.repository.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceWithCustomTable implements UserDetailsService {

	private final CustomerRepo customerRepo;

	// here email is the username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("** by Dj **User not found for username: " + username));
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
		return new User(customer.getEmail(), customer.getPwd(), authorities);
	}

}
