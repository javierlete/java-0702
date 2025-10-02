package com.ipartek.formacion.servlets;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
		
		var out = response.getWriter();
		
		out.println("<ul>");
		
		dao.obtenerTodos().forEach(cita -> out.printf("""
				<li><a href="detalle?id=%s">%s</a></li>
				""", cita.getId(), cita.getTexto(), cita.getInicio()));

		out.println("</ul>");
	}

}
