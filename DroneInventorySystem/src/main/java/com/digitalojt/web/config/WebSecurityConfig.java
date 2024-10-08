package com.digitalojt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig
 *
 * @author your name
 * 
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	/**
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// LoginController呼び出し
		http.formLogin(login -> login.loginPage("/login/").usernameParameter("adminId"));
		
		return http.build();
	}
}
