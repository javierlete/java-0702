<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-hover table-striped table-bordered">
	<thead class="table-secondary">
		<tr>
			<th>Id</th>
			<td>Matrícula</td>
			<td>Marca</td>
			<td>Modelo</td>
			<td>Propietario</td>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${vehiculos}" var="v">
			<tr>
				<th>${v.id}</th>
				<td>${v.matricula}</td>
				<td>${v.marca}</td>
				<td>${v.modelo}</td>
				<td>${v.propietario.nombre}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>