<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<p class="lead text-center fw-medium">${usuario.nombre}: ${usuario.email}</p>
<p class="lead text-center fw-medium">${usuario.rol.nombre}</p>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>