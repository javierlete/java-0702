<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="card text-center">
	<div class="card-header lead fw-medium">Vehículo</div>
	<ul class="list-group list-group-flush">
		<li class="list-group-item"><span class="fw-medium">Matrícula</span>:
			${vehiculo.matricula}</li>
		<li class="list-group-item"><span class="fw-medium">Bastidor</span>:
			${vehiculo.bastidor}</li>
		<li class="list-group-item"><span class="fw-medium">Estado
				de reparación</span>: ${vehiculo.estadoReparacion}</li>
	</ul>
</div>


<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
