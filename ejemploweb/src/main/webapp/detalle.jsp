<%@page import="com.ipartek.formacion.bibliotecas.Fabrica"%>
<%@page import="com.ipartek.formacion.citas.accesodatos.DaoCita"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle</title>
</head>
<body>

	<%
	var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

	var cita = dao.obtenerPorId(Long.parseLong(request.getParameter("id"))).orElse(null);
	%>

	<div>
		<%
		if (cita != null) {
		%>
		<p>
			Id:
			<%=cita.getId()%></p>
		<p>
			Texto:
			<%=cita.getTexto()%></p>
		<p>
			Inicio:
			<%=cita.getInicio()%></p>
		<p>
			Fin:
			<%=cita.getFin()%></p>
		<%
		}
		%>
	</div>

</body>
</html>