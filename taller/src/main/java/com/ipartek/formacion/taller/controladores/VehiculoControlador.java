package com.ipartek.formacion.taller.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/vehiculo/*")
public class VehiculoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoVehiculo DAO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String path = request.getPathInfo();

			System.out.println(path);

			for (Method metodo : getClass().getDeclaredMethods()) {
				System.out.println(metodo.getName());

				Ruta ruta = metodo.getAnnotation(Ruta.class);

				System.out.println(ruta);

				if (ruta != null && ruta.value().equals(path)) {
					metodo.invoke(this, request, response);
					return;
				}
			}

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Ruta("/listado")
	private void listado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var vehiculos = DAO.obtenerTodos();

		request.setAttribute("vehiculos", vehiculos);

		request.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(request, response);
	}

	@Ruta("/detalle")
	private void detalle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String matricula = request.getParameter("matricula");

		var vehiculo = DAO.buscarPorMatricula(matricula);

		request.setAttribute("vehiculo", vehiculo.orElse(null));

		request.getRequestDispatcher("/WEB-INF/vistas/detalle.jsp").forward(request, response);
	}

}
