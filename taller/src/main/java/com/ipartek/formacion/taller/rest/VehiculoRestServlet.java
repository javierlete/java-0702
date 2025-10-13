package com.ipartek.formacion.taller.rest;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;
import com.ipartek.formacion.taller.modelos.Vehiculo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/vehiculos/*")
public class VehiculoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoVehiculo dao = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");
	private static final Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String path = request.getPathInfo().substring(1);

		PrintWriter out = response.getWriter();

		if (path.isBlank()) {
			out.append(gson.toJson(dao.obtenerTodos()));
		} else {
			Long id = Long.parseLong(path);

			var vehiculo = dao.obtenerPorId(id).get();

			out.append(gson.toJson(vehiculo));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		var cuerpo = request.getReader();

		var vehiculo = gson.fromJson(cuerpo, Vehiculo.class);
		
		var vehiculoResultante = dao.insertar(vehiculo);
		
		PrintWriter out = response.getWriter();

		out.append(gson.toJson(vehiculoResultante));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		var cuerpo = request.getReader();

		var vehiculo = gson.fromJson(cuerpo, Vehiculo.class);

		String path = request.getPathInfo().substring(1);

		Long id = Long.parseLong(path);
		
		var vehiculoResultante = dao.modificar(vehiculo);
		
		PrintWriter out = response.getWriter();

		out.append(gson.toJson(vehiculoResultante));

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo().substring(1);

		Long id = Long.parseLong(path);
		
		dao.borrar(id);
	}
}
