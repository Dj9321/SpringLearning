package com.dj.learning.spring.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dj.learning.spring.boot.entity.Customer;
import com.dj.learning.spring.boot.repository.CustomerRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final CustomerRepo customerRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		try {
			// by default it will use bcrypt to encode the password.
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			System.out.println("hashPwd" + hashPwd);
			customer.setPwd(hashPwd);
			Customer savedCustomer = customerRepository.save(customer);

			if (savedCustomer.getId() > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully registered");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occurred: " + ex.getMessage());
		}

	}

}
