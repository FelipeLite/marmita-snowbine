package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.UsuarioDao;
import br.com.snowbine.sistema.entity.Usuario;

@Named
@ViewScoped
public class UsuarioListBean extends BaseBeanLista<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = -1357026317790380693L;
	
	@PostConstruct
	public void init()
	{
		super.listar();
	}
	
}
