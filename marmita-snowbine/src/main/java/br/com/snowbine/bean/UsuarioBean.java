package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.UsuarioDao;
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
	
	public String logar()
	{
		System.out.println("[Login e verificação]");
		if(!this.validarLoginSenha())
		{
			return null;
		}
		
		return null;
	}
	
	private boolean validarLoginSenha()
	{
		String login = this.getEntidade().getLogin().trim();
		String senha = this.getEntidade().getSenha().trim();
		
		
		try
		{
			//Procura pelo usuario
			Integer resultado = this.getDao().consultarPorParametros("t.login = '" + login + "'").size();
			System.out.println(resultado);
			
			if(resultado == 0)
			{
				System.out.println("Usuario nao encontrado!");
				return false;
			}
			
			else
			{
				//Encontra o usuario e compara a senha
				Usuario usuarioEncontrado = this.getDao().consultarPorParametros("login =" + login).get(0);
				
				if(!StringUtils.criptografarSenha(senha).equals(usuarioEncontrado.getSenha()))
				{
					System.out.println("Usuario ou senha incorretos!");
					
					return false;
				}
			}
			
			System.out.println("login e senha ok");
			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
