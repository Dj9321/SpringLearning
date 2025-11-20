package com.dj.learning.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentTemp {

	@Autowired
	Environment environment;

	public String getEnvironment() {
		System.out.println("*** " + (environment.getActiveProfiles())[0]);
		return (environment.getActiveProfiles())[0];
	}
}
