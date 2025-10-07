package com.ipartek.formacion.bibliotecas.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
			String path = request.getPathInfo();

			for (ClassInfo classInfo : scanResult.getClassesWithMethodAnnotation(Ruta.class)) {
				Class<?> clase = classInfo.loadClass();

				for (MethodInfo methodInfo : classInfo.getMethodInfo()) {
					AnnotationInfo anotacion = methodInfo.getAnnotationInfo(Ruta.class);
					
					if (anotacion != null) {
						Object valor = anotacion.getParameterValues().getValue("value");
						
						if (path.equals(valor)) {
							Method method = methodInfo.loadClassAndGetMethod();

							Object instancia = clase.getDeclaredConstructor().newInstance();

							System.out.println("Invocando " + clase.getName() + "." + method.getName());
							
							Map<String, String> entrada = new HashMap<String, String>();
							Map<String, Object> salida = new HashMap<String, Object>();
							
							request.getParameterMap().forEach((clave, array) -> {
								entrada.put(clave, array[0]);
							});
							
							String vista = (String) method.invoke(instancia, entrada, salida);
							
							salida.forEach((k, v) -> request.setAttribute(k, v));
							
							request.getRequestDispatcher("/WEB-INF/vistas/" + vista + ".jsp").forward(request, response);
						}
					}
				}
			}
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
