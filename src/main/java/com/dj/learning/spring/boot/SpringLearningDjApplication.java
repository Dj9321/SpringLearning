package com.dj.learning.spring.boot;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// the enable jpa repository tells to scan repositories in this package. Useful if it is outside subdirectories of spring.
//@EnableJpaRepositories("com.dj.learning.spring.boot.repository")
//@EntityScan("com.dj.learning.spring.boot") // useful when code is outside spring boot main class sub directories
public class SpringLearningDjApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringLearningDjApplication.class, args);
		Environment environment = context.getEnvironment();
		for (String a : environment.getActiveProfiles())
			System.out.println(a);
		System.out.println((environment.getActiveProfiles())[0]);
	}
}
