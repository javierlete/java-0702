package com.ipartek.formacion.springtaller.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ipartek.formacion.springtaller.controladores.LoginController;
import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;

@Configuration
public class WebSecurityConfig {

    private final LoginController loginController;
	@Autowired
	private UsuarioRepository usuarioRepository;

    WebSecurityConfig(LoginController loginController) {
        this.loginController = loginController;
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			var oUsuario = usuarioRepository.findByEmail(username);

			System.out.println(oUsuario);
			
			if (oUsuario.isEmpty()) {
				throw new UsernameNotFoundException("No se ha encontrado el usuario " + username);
			}

			var usuarioLogin = new UsuarioLogin();
			
			BeanUtils.copyProperties(oUsuario.get(), usuarioLogin);
			
			System.out.println(usuarioLogin);
			
			return usuarioLogin;
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
