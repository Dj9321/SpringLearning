package com.dj.learning.spring.boot.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
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
// .redirectToHttps(Customizer.withDefaults()) > use this in Prod for https only 
		http.csrf(csrfConfig -> csrfConfig.disable())
				// /abcUrl/** > after this url whatever comes we will permit > 2 Stars **
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/check", "/error", "/register")
						.permitAll().requestMatchers("/callExternalWebsite", "/jsonMapper", "/checkMailProperties")
						// .hasAnyRole("read", "user", "OAUTH2_USER").anyRequest()
						.authenticated());
		// we can configure for login page as well by below line http.formLogin()
		http.formLogin(withDefaults());
//		http.formLogin(flc -> flc.disable());
//		http.httpBasic(withDefaults());
//		http.httpBasic(hbc -> hbc.disable());
		// Session Timeout url page
		http.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession")); // if we have MVC
		// .expiredUrl() > for expired urls in MVC
		http.sessionManagement(s -> s.maximumSessions(2).maxSessionsPreventsLogin(true));
		http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
		// This is a Global config whenever an exception occurs this will be called.
		// This is global as it will be called for all other flows not just with login
		// page.
//		http.exceptionHandling(eh -> eh.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
		// we can also add accessDeniedPage("/customErrorPage") to this to redirect to
		// the error page
		http.exceptionHandling(eh -> eh.accessDeniedHandler(new CustomAccessDeniedHandler()));
		http.oauth2Login(Customizer.withDefaults()); // enable OAuth2 login
		return http.build();
	}

	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration github = githubClientRegistration();
//		ClientRegistration facebook = facebookClientRegistration();
		return new InMemoryClientRegistrationRepository(github); // , facebook);
		// give clue to OAuth2 which Auth Server we are using.
	}

	// once we register we will get clientId & client secret with github or others.
	// for gitHub go to > profile > settings > Developer options > OAuth Apps >
	private ClientRegistration githubClientRegistration() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23liVplacGriBNwZFB")
				.clientSecret("93aaacbb205741dd26fe8378470955f529872b7a").build();
	}

//	private ClientRegistration facebookClientRegistration(){	
//	    CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId().clientSecret()
//	}

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

	// By default bcrypt will be used
	@Bean
	public PasswordEncoder passwordEncoder() {
		// leave to spring to choose the password encoder > bcrypt default for now in
		// spring
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

	// if we want to change security context strategy: default is: MODE_THREAD_LOCAL
	// Others: MODE_INHERITABLE_THREAD_LOCAL, MODE_GLOBAL
//	@Bean
//	public InitializingBean initializingBean() {
//	    return () -> SecurityContextHolder().setStrategyName(SecurityContextHodler.MODE_INHERITABLE_THREAD_LOCAL);
//	}

}
