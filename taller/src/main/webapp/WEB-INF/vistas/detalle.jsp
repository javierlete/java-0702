<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="card text-center">
	<div class="card-header">Featured</div>
	<ul class="list-group list-group-flush">
		<li class="list-group-item"><span class="fw-bold">Matrícula</span>:
			${vehiculo.matricula}</li>
		<li class="list-group-item"><span class="fw-bold">Bastidor</span>:
			${vehiculo.bastidor}</li>
		<li class="list-group-item"><span class="fw-bold">Estado
				de reparación</span>: ${vehiculo.estadoReparacion}</li>
	</ul>
</div>


<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
