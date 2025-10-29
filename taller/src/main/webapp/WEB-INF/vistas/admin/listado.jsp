<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-hover table-striped table-bordered">
	<thead class="table-secondary">
		<tr>
			<th>Id</th>
			<th>Matrícula</th>
			<th>Marca</th>
			<th>Modelo</th>
			<th>Propietario</th>
			<th>OPCIONES</th>
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
				<td><a class="btn btn-sm btn-primary" href="admin/formulario?id=${v.id}">Editar</a>
					<a class="btn btn-sm btn-danger" href="admin/borrar?id=${v.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot class="table-secondary">
		<tr>
			<td colspan="5"></td>
			<td><a class="btn btn-sm btn-primary" href="admin/formulario">Añadir</a>
			</td>

		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>