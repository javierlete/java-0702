package com.ipartek.formacion.bibliotecas.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
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
import jakarta.servlet.http.HttpSession;

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
							llamarARuta(request, response, clase, methodInfo);

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

	private void llamarARuta(HttpServletRequest request, HttpServletResponse response, Class<?> clase,
			MethodInfo methodInfo) throws InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, ServletException, IOException {
		Method method = methodInfo.loadClassAndGetMethod();

		Object instancia = clase.getDeclaredConstructor().newInstance();

		log.log(Level.INFO, "LLAMADA A METODO: {0}.{1}()", new Object[] { clase.getName(), method.getName() });

		Map<String, Object> salida = new HashMap<String, Object>();
		Map<String, String> entrada;

		entrada = crearEntrada(request);

		String vista = invocarMetodo(method, instancia, salida, entrada);

		crearSalida(request, salida);

		log.log(Level.FINE, "VISTA: {0}", vista);

		String pathVistaAbsoluto = "/WEB-INF/vistas/" + vista + ".jsp";

		log.log(Level.FINER, "VISTA ABSOLUTO: {0}", pathVistaAbsoluto);

		request.getRequestDispatcher(pathVistaAbsoluto).forward(request, response);
	}

	private void crearSalida(HttpServletRequest request, Map<String, Object> salida) {
		salida.forEach((k, v) -> {
			if (k.equals("sesion") && v.equals("invalidar")) {
				request.getSession().invalidate();
			} else if (k.startsWith("sesion.")) {
				request.getSession().setAttribute(k.replace("sesion.", ""), v);
			} else {
				request.setAttribute(k, v);
			}
		});

		log.log(Level.FINE, "SALIDA: {0}", salida);
	}

	private String invocarMetodo(Method method, Object instancia, Map<String, Object> salida,
			Map<String, String> entrada) throws IllegalAccessException, InvocationTargetException {
		return switch (method.getParameterCount()) {
		case 2 -> (String) method.invoke(instancia, entrada, salida);
		case 1 -> (String) method.invoke(instancia, salida);
		case 0 -> (String) method.invoke(instancia);
		default -> throw new IllegalArgumentException();
		};
	}

	private Map<String, String> crearEntrada(HttpServletRequest request) {
		Map<String, String> entrada = new HashMap<String, String>();

		request.getParameterMap().forEach((clave, array) -> {
			entrada.put(clave, array[0]);
		});

		HttpSession session = request.getSession();
		Enumeration<String> atributosSesion = session.getAttributeNames();

		while (atributosSesion.hasMoreElements()) {
			String atributo = atributosSesion.nextElement();
			entrada.put("sesion." + atributo, (String) session.getAttribute(atributo));
		}

		log.log(Level.FINE, "ENTRADA: {0}", entrada);

		return entrada;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
