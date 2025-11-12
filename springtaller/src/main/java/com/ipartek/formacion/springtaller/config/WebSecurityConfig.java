package com.ipartek.formacion.springtaller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y, 14);
	}

	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
			.requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
			.anyRequest().authenticated()
		)
		.formLogin(form -> form.loginPage("/login").permitAll())
		.logout(logout -> logout.permitAll());

		return http.build();
	}
}
