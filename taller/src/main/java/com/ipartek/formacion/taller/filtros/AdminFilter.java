package com.ipartek.formacion.taller.filtros;

import java.io.IOException;

import com.ipartek.formacion.taller.modelos.Usuario;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/cf/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		var request = (HttpServletRequest) req;
		var response = (HttpServletResponse) res;
		
		var session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario == null || !"ADMIN".equals(usuario.getRol().getNombre())) {
			response.sendRedirect(request.getContextPath() + "/cf/login");
			return;
		}
		
		chain.doFilter(req, res);
	}

}
