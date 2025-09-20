//package com.dj.learning.spring.boot.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurationDj extends WebSecurityConfiguration {
//
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/oauth2/**", "/login/**", "/logout/**").permitAll().anyRequest()
//				.authenticated().and().oauth2Login().loginPage("/login").defaultSuccessURL("/home").and().logout()
//				.logoutSuccessUrl("/").logoutUrl("/logout").and().csrf().disable();
//	}
//
//}
