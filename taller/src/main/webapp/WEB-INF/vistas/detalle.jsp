<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<dl>
		<dt>Matrícula</dt>
		<dd>${vehiculo.matricula}</dd>
		
		<dt>Bastidor</dt>
		<dd>${vehiculo.bastidor}</dd>
		
		<dt>Estado de reparación</dt>
		<dd>${vehiculo.estadoReparacion}</dd>
	</dl>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
