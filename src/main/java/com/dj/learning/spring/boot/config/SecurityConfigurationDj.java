package com.dj.learning.spring.boot.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@EnableWebSecurity // this is also not mandatory as spring will detect based on dependencies
public class SecurityConfigurationDj {

//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/oauth2/**", "/login/**", "/logout/**").permitAll().anyRequest()
//				.authenticated().and().oauth2Login().loginPage("/login").defaultSuccessURL("/home").and().logout()
//				.logoutSuccessUrl("/").logoutUrl("/logout").and().csrf().disable();
//	}

	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/check", "/error").permitAll()
				.requestMatchers("/callExternalWebsite", "/jsonMapper").authenticated());
		http.formLogin(withDefaults());
//		http.formLogin(flc -> flc.disable());
//		http.httpBasic(withDefaults());
		http.httpBasic(hbc -> hbc.disable());
		return http.build();
	}

	/**
	 * We created a bean of UserDetailsService > an implementation is
	 * InMemoryUserDetailsManager
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//		// Sand@dj22
//		UserDetails read = User.withUsername("user")
//				.password("Sand@345").authorities("read")
//				.build();
//		UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$af8x3jc.amnpMDDZwwwyOe3uFSBMBF8y1vtrw34/41BIocXfR0AHK").authorities("admin").build();
//		return new InMemoryUserDetailsManager(read, admin);
//	}

	// javax.sql.Datasource. Getting userDetails from Database
	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource datasource) {
	 * return new JdbcUserDetailsManager(datasource); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		// It will ask a public api to check your password against common passwords
		// You can also implement your own implementation as well.
		// Will prevent to login if your password is not complex like simple words :
		// Sand
		return new HaveIBeenPwnedRestApiPasswordChecker();

	}
}
