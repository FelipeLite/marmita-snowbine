package br.com.snowbine.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.snowbine.sistema.entity.Usuario;

public class LoginFilter implements Filter
{

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException
	{
		Usuario usuario = null;
		HttpSession sess = ((HttpServletRequest) request).getSession(false);

		if (sess != null)
		{
			usuario = (Usuario) sess.getAttribute("usuarioLogado");
		}

		if (usuario == null)
		{
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/security/login.xhtml");
		} 
		
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

}
