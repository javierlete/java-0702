package com.ipartek.formacion.amazonia.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipartek.formacion.amazonia.modelos.Categoria;
import com.ipartek.formacion.amazonia.modelos.Producto;

public interface AnonimoService {
	Page<Producto> listadoProductos(Pageable pageable);
	Page<Producto> listadoProductos(Pageable pageable, Long idCategoria);
	Producto detalleProducto(Long id);
	
	Page<Categoria> listadoCategorias(Pageable pageable);
	Categoria detalleCategoria(Long idCategoria);
}
