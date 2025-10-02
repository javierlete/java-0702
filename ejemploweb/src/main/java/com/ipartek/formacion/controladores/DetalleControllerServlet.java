package com.ipartek.formacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/citas/detalle")
public class DetalleControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recojer datos de la petición
		String sId = request.getParameter("id");
		
		// Convertir los que sea necesario
		Long id = Long.parseLong(sId);
		
		// Crear objeto con los datos
		// Lógica de negocio
		var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
		
		var cita = dao.obtenerPorId(id).orElse(null);
		
		// Empaquetar objetos para la siguiente vista
		request.setAttribute("cita", cita);
		
		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/detalle.jsp").forward(request, response);
	}

}
