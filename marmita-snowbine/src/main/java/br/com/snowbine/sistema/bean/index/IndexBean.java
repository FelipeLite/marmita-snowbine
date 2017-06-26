package br.com.snowbine.sistema.bean.index;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.SessionUtil;

@Named
@SessionScoped
public class IndexBean
{
	private Usuario usuarioLogado;
	
	public void setUsuarioLogado(Usuario usuario)
	{
		this.usuarioLogado = usuario;
	}
	
	public Usuario getUsuarioLogado()
	{
		return this.usuarioLogado;
	}
	
	public IndexBean()
	{
		this.setUsuarioLogado((Usuario)SessionUtil.getParam("usuarioLogado"));
	}
	
	@PostConstruct
	public void init()
	{
	}
}

