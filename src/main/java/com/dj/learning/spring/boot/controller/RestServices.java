package com.dj.learning.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dj.learning.spring.boot.service.DataService;

@RestController
public class RestServices {

	@Autowired
	DataService dataService;

	@GetMapping("/check")
	public String applicationCheck() {
		dataService.saveDataInCreatedByDB();
		return "Application is up and running";
	}
}
