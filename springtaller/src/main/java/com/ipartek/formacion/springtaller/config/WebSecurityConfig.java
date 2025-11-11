package com.ipartek.formacion.springtaller.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;

@Configuration
public class WebSecurityConfig {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			var usuario = usuarioRepository.findByEmail(username);

			if (usuario.isEmpty()) {
				throw new UsernameNotFoundException("No se ha encontrado el usuario " + username);
			}

			return new UserDetails() {

				@Override
				public String getUsername() {
					return usuario.get().getEmail();
				}

				@Override
				public String getPassword() {
					return usuario.get().getPassword();
				}

				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					return List.of(() -> "ROLE_" + usuario.get().getRol().getNombre());
				}
			};
		};
	}

	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				requests -> requests.requestMatchers("/admin/**").hasRole("ADMINISTRADOR").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll()).logout(logout -> logout.permitAll());

		return http.build();
	}
}
