package com.ipartek.formacion.servlets;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detalle")
public class DetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

		var out = response.getWriter();

		out.println("<ul>");

		var oCita = dao.obtenerPorId(id);

		var cita = oCita.orElse(null);

		if (cita != null) {
			out.printf("""
					<p>Id:     %s</p>
					<p>Texto:  %s</p>
					<p>Inicio: %s</p>
					<p>Fin:    %s</p>
					""", cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin());
		} else {
			out.println("No encontrado");
		}

		out.println("</ul>");
	}

}
