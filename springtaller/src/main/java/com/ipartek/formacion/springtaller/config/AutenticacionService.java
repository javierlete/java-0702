package com.ipartek.formacion.springtaller.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var oUsuario = usuarioRepository.findByEmail(username);

		System.out.println(oUsuario);

		if (oUsuario.isEmpty()) {
			throw new UsernameNotFoundException("No se ha encontrado el usuario " + username);
		}

		var usuarioLogin = new UsuarioLogin();

		BeanUtils.copyProperties(oUsuario.get(), usuarioLogin);

		System.out.println(usuarioLogin);

		return usuarioLogin;
	}

}
