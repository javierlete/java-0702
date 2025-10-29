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

<form action="formulariopost" method="post">
	<div class="row mb-3">
		<label for="email" class="col-sm-2 col-form-label">Email</label>
		<div class="col-sm-10">
			<input type="email" class="form-control ${errores == null ? '' : (errores.email != null ? 'is-invalid' : 'is-valid') }" id="email" name="email"
				value="${email}">
			<div class="invalid-feedback">${errores.email}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
		<div class="col-sm-10">
			<input type="password" class="form-control ${errores == null ? '' : (errores.password != null ? 'is-invalid' : 'is-valid') }" id="password"
				name="password">
			<div class="invalid-feedback">${errores.password}</div>
		</div>
	</div>
	<div id="capa-nombre" class="row mb-3 ${errores != null ? '' : 'd-none' }">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" class="form-control ${errores == null ? '' : (errores.nombre != null ? 'is-invalid' : 'is-valid') }" id="nombre" name="nombre" value="${nombre}">
			<div class="invalid-feedback">${errores.nombre}</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="registro" class="col-sm-2 form-check-label">Registro</label>
		<div class="col-sm-10">
			<input type="checkbox" ${errores != null ? 'checked' : '' } class="form-check-input" id="registro"
				name="registro" onclick="conmutarInicioSesionRegistro(this)">
		</div>
	</div>

	<div class="row mb-3">
		<div class="offset-sm-2 col-sm-10">
			<button id="boton" type="submit" class="btn btn-primary">Iniciar
				sesión</button>
		</div>
	</div>

</form>

<script>
	function conmutarInicioSesionRegistro(verificacion) {
		const capaNombre = document.getElementById('capa-nombre');
		const boton = document.getElementById('boton');

		if (verificacion.checked) {
			capaNombre.classList.remove('d-none');
			boton.innerText = 'Registro';
		} else {
			capaNombre.classList.add('d-none')
			boton.innerText = 'Inicio sesión';
		}

		
	}
</script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>