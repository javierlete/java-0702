package com.ipartek.formacion.springtaller.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springtaller.entidades.Usuario;
import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class AnonimoServiceImpl implements AnonimoService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		var usuario = usuarioRepository.findByEmail(email);

		if (usuario.isPresent() && !usuario.get().getPassword().equals(password)) {
			return Optional.empty();
		}

		return usuario;
	}

	@Override
	public Usuario registrar(@Valid Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

}
