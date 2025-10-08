<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<ul class="list-group">
	<c:forEach items="${vehiculos}" var="v">
		<li class="list-group-item text-center">
			<a class="link-underline link-underline-opacity-0 lead fs-1" href="detalle?matricula=${v.matricula}">${v.matricula}</a>
		</li>
	</c:forEach>
</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>