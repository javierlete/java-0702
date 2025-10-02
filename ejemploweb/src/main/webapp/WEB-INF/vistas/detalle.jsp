<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado</title>
</head>
<body>

<c:if test="${cita != null}">
	<p>${cita.id}</p>
	<p>${cita.texto}</p>
	<p>${cita.inicio}</p>
	<p>${cita.fin}</p>
</c:if>

<c:if test="${cita == null}">
	<p>No se ha encontrado la cita</p>
</c:if>

</body>
</html>