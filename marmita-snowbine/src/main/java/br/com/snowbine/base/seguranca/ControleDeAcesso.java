package br.com.snowbine.base.seguranca;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames ={ "Faces Servlet" })
public class ControleDeAcesso implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if ((session.getAttribute("usuarioLogado") != null) || (req.getRequestURI().endsWith("login.xhtml")) || (req.getRequestURI().contains("javax.faces.resource/")) || req.getRequestURI().endsWith("marmita-snowbine/") || req.getRequestURI().endsWith("criar-conta.xhtml"))
		{
			chain.doFilter(request, response);
		}

		else
		{
			redireciona("/marmita-snowbine/", response);
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	public void destroy()
	{
	}

	private void redireciona(String url, ServletResponse response) throws IOException
	{
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}
