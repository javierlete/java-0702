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

<form action="listado" method="post">
	<input type="text" placeholder="Texto" name="texto">
	<input type="datetime-local" placeholder="Inicio" name="inicio">
	<input type="datetime-local" placeholder="Fin" name="fin">

	<button>Guardar</button>
</form>

<c:forEach items="${citas}" var="c">
	<p>${c.id}: <a href="detalle?id=${c.id}">${c.texto}</a> => (${c.inicio}, ${c.fin})</p>
</c:forEach>

</body>
</html>