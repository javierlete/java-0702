package com.ipartek.formacion.taller.controladores;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoVehiculo DAO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var vehiculos = DAO.obtenerTodos();

		request.setAttribute("vehiculos", vehiculos);

		request.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(request, response);

	}

}
