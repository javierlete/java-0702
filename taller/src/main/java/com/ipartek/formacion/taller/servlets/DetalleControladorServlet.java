package com.ipartek.formacion.taller.servlets;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detalle")
public class DetalleControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoVehiculo DAO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String matricula = request.getParameter("matricula");
		
		var vehiculo = DAO.buscarPorMatricula(matricula);

		request.setAttribute("vehiculo", vehiculo.orElse(null));

		request.getRequestDispatcher("/WEB-INF/vistas/detalle.jsp").forward(request, response);

	}

}
