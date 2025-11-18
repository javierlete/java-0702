package com.ipartek.formacion.amazonia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.amazonia.modelos.Categoria;
import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.amazonia.repositorios.ProductoRepository;

import lombok.extern.java.Log;

@Log
@Service
public class AnonimoServiceImpl implements AnonimoService {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
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
	
}
