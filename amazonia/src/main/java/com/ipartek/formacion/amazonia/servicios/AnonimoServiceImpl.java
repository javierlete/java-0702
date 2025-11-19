package com.ipartek.formacion.amazonia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.amazonia.modelos.Categoria;
import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.modelos.Usuario;
import com.ipartek.formacion.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.amazonia.repositorios.RolRepository;
import com.ipartek.formacion.amazonia.repositorios.UsuarioRepository;

import lombok.extern.java.Log;

@Log
@Service
public class AnonimoServiceImpl implements AnonimoService {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public Page<Producto> listadoProductos(Pageable pageable) {
		log.info("Se han pedido los productos de la página " + pageable.getPageNumber() + " con tamaño " + pageable.getPageSize());
		return productoRepository.findAll(pageable);
	}

	@Override
	public Page<Producto> listadoProductos(Pageable pageable, Long idCategoria) {
		return productoRepository.findByCategoriaId(pageable, idCategoria);
	}

	@Override
	public Producto detalleProducto(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Categoria> listadoCategorias(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	public Categoria detalleCategoria(Long idCategoria) {
		return categoriaRepository.findById(idCategoria).orElse(null);
	}

	@Override
	public Usuario registrarse(Usuario usuario) {
		var rolUsuario = rolRepository.findByNombre("USUARIO");
		
		if(rolUsuario.isEmpty()) {
			throw new ServicioException("No se ha encontrado el rol USUARIO");
		}
		
		usuario.setRol(rolUsuario.get());
		
		return usuarioRepository.save(usuario);
	}
}
