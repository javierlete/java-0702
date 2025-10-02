<%@page import="com.ipartek.formacion.bibliotecas.Fabrica"%>
<%@page import="com.ipartek.formacion.citas.accesodatos.DaoCita"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado</title>
</head>
<body>

	<%
	var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

	var citas = dao.obtenerTodos();
	%>

	<ul>
		<%
		for (var cita : citas) {
		%>
		<li><a href="detalle.jsp?id=<%=cita.getId()%>"><%=cita.getTexto()%></a></li>
		<%
		}
		%>


	</ul>

</body>
</html>