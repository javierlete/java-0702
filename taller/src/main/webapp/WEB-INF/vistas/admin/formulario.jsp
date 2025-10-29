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

<p>${errores}</p>

<form action="admin/formulariopost" method="post">
	<input type="hidden" name="id" value="${vehiculo.id}">

	<div class="row mb-3">
		<label for="matricula" class="col-sm-2 col-form-label">Matrícula</label>
		<div class="col-sm-10">
			<input type="text"
				class="form-control ${errores == null ? '' : (errores.matricula != null ? 'is-invalid' : 'is-valid') }"
				id="matricula" name="matricula" value="${vehiculo.matricula}">
			<div class="invalid-feedback">${errores.matricula}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="marca" class="col-sm-2 col-form-label">Marca</label>
		<div class="col-sm-10">
			<input type="text"
				class="form-control ${errores == null ? '' : (errores.marca != null ? 'is-invalid' : 'is-valid') }"
				id="marca" name="marca" value="${vehiculo.marca}">
			<div class="invalid-feedback">${errores.marca}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="modelo" class="col-sm-2 col-form-label">Modelo</label>
		<div class="col-sm-10">
			<input type="text"
				class="form-control ${errores == null ? '' : (errores.modelo != null ? 'is-invalid' : 'is-valid') }"
				id="modelo" name="modelo" value="${vehiculo.modelo}">
			<div class="invalid-feedback">${errores.modelo}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="bastidor" class="col-sm-2 col-form-label">Bastidor</label>
		<div class="col-sm-10">
			<input type="text"
				class="form-control ${errores == null ? '' : (errores.bastidor != null ? 'is-invalid' : 'is-valid') }"
				id="bastidor" name="bastidor" value="${vehiculo.bastidor}">
			<div class="invalid-feedback">${errores.bastidor}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="propietario" class="col-sm-2 col-form-label">Propietario</label>
		<div class="col-sm-10">
			<select type="text"
				class="form-select ${errores == null ? '' : (errores.propietario != null ? 'is-invalid' : 'is-valid') }"
				id="propietario" name="propietario">
				
				<c:forEach items="${propietarios}" var="propietario">
					<option value="${propietario.id}" ${propietario.id == vehiculo.propietario.id ? 'selected': ''}>${propietario.nombre}</option>
				</c:forEach>
			</select>
			<div class="invalid-feedback">${errores.propietario}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="estado" class="col-sm-2 col-form-label">Estado Reparación</label>
		<div class="col-sm-10">
			<select type="text"
				class="form-select ${errores == null ? '' : (errores.estado != null ? 'is-invalid' : 'is-valid') }"
				id="estado" name="estado">
				
				<c:forEach items="${estados}" var="estado">				
					<option ${estado == vehiculo.estadoReparacion ? 'selected' : ''}>${estado}</option>
				</c:forEach>
			</select>
			<div class="invalid-feedback">${errores.estado}</div>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm-10">
			<button id="boton" type="submit" class="btn btn-primary">Guardar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>