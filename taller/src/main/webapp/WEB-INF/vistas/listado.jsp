<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<ul>
	<c:forEach items="${vehiculos}" var="v">
	<li>
		<a href="detalle?matricula=${v.matricula}">${v.matricula}</a>
	</li>
	</c:forEach>
</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>