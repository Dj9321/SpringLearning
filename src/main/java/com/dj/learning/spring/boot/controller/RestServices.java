package com.dj.learning.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServices {

	@GetMapping("/check")
	public String applicationCheck() {
		return "Application is up and running";
	}
}
