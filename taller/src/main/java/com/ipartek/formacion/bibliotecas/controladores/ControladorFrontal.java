package com.ipartek.formacion.bibliotecas.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.classgraph.AnnotationInfo;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.ScanResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ControladorFrontal.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
			String path = request.getPathInfo();

			log.log(Level.INFO, "PATHINFO: {0}", path);

			for (ClassInfo classInfo : scanResult.getClassesWithMethodAnnotation(Ruta.class)) {
				log.log(Level.FINEST, "CLASE?: {0}", classInfo.getName());

				Class<?> clase = classInfo.loadClass();

				for (MethodInfo methodInfo : classInfo.getMethodInfo()) {
					log.log(Level.FINEST, "METODO?: {0}", methodInfo.getName());

					AnnotationInfo anotacion = methodInfo.getAnnotationInfo(Ruta.class);

					log.log(Level.FINEST, "ANOTACION?: {0}", anotacion);

					if (anotacion != null) {

						Object valor = anotacion.getParameterValues().getValue("value");

						log.log(Level.FINEST, "VALOR?: {0}", valor);

						if (path.equals(valor)) {
							Method method = methodInfo.loadClassAndGetMethod();

							Object instancia = clase.getDeclaredConstructor().newInstance();

							log.log(Level.INFO, "LLAMADA A METODO: {0}.{1}()", new Object[] { clase.getName(), method.getName() });

							Map<String, String> entrada = new HashMap<String, String>();
							Map<String, Object> salida = new HashMap<String, Object>();

							request.getParameterMap().forEach((clave, array) -> {
								entrada.put(clave, array[0]);
							});

							log.log(Level.FINE, "ENTRADA: {0}", entrada);

							String vista = (String) method.invoke(instancia, entrada, salida);

							salida.forEach((k, v) -> request.setAttribute(k, v));

							log.log(Level.FINE, "SALIDA: {0}", salida);
							log.log(Level.FINE, "VISTA: {0}", vista);

							String pathVistaAbsoluto = "/WEB-INF/vistas/" + vista + ".jsp";
							
							log.log(Level.FINER, "VISTA ABSOLUTO: {0}", pathVistaAbsoluto);
							
							request.getRequestDispatcher(pathVistaAbsoluto).forward(request,
									response);

							return;
						}
					}
				}
			}

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
