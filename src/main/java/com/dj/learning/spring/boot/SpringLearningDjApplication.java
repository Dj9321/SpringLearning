package com.dj.learning.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// the enable jpa repository tells to scan repositories in this package. Useful if it is outside subdirectories of spring.
//@EnableJpaRepositories("com.dj.learning.spring.boot.repository")
//@EntityScan("com.dj.learning.spring.boot") // useful when code is outside spring boot main class sub directories
public class SpringLearningDjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLearningDjApplication.class, args);
	}
}
