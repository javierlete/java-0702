<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<c:if test="${error != null}">
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		${error}
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
</c:if>

<form action="admin/formulariopost" method="post">
	<input type="hidden" name="id" value="${vehiculo.id}">

	<jl:labelinput nombre="matricula" etiqueta="Matrícula" valor="${vehiculo.matricula}" />
	<jl:labelinput nombre="marca" etiqueta="Marca" valor="${vehiculo.marca}" />
	<jl:labelinput nombre="modelo" etiqueta="Modelo" valor="${vehiculo.modelo}" />
	<jl:labelinput nombre="bastidor" etiqueta="Bastidor" valor="${vehiculo.bastidor}" />
	<jl:labelinput nombre="propietario" etiqueta="Propietario" tipo="select" opciones="${propietarios}" valor="${vehiculo.propietario.id}" />
	<jl:labelinput nombre="estado" etiqueta="Estado Reparación" tipo="select" opciones="${estados}" valor="${vehiculo.estadoReparacion}" />
	
	<jl:labelinput tipo="submit" etiqueta="Guardar"/>
	
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>