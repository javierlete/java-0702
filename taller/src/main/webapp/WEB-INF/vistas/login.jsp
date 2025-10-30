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
	<jl:labelinput nombre="email" tipo="email" etiqueta="Email" valor="${email}"/>
	<jl:labelinput nombre="password" tipo="password" etiqueta="Contraseña"/>
	<jl:labelinput id="capa-nombre" nombre="nombre" tipo="text" etiqueta="Nombre" valor="${nombre}"/>
	<jl:labelinput nombre="registro" tipo="checkbox" etiqueta="Registro" valor="${errores != null}" onclick="conmutarInicioSesionRegistro(this)"/>
	<jl:labelinput idBoton="boton" tipo="submit" etiqueta="Iniciar sesión" />
</form>

<script>
	const capaNombre = document.getElementById('capa-nombre');
	const boton = document.getElementById('boton');
	
	capaNombre.classList.add('d-none')
	
	function conmutarInicioSesionRegistro(verificacion) {
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