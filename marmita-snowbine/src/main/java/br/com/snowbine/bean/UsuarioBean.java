package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.EstadoDao;
import br.com.snowbine.dao.UsuarioDao;
import br.com.snowbine.entity.Estado;
import br.com.snowbine.entity.Usuario;
import br.com.snowbine.util.StringUtils;

@ManagedBean
@SessionScoped
public class UsuarioBean extends BaseBean<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = -1357026317790380693L;
	
	//Criptografa a senha
	@Override
	public String cadastrar()
	{
		this.getEntidade().setSenha(StringUtils.criptografarSenha(this.getEntidade().getSenha()));
		
		return super.cadastrar();
	}
}
