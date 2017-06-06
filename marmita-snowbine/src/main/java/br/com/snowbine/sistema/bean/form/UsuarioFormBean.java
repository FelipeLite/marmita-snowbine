package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.UsuarioDao;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.StringUtils;
import br.com.snowbine.sistema.util.UploadUtils;

@ManagedBean
@ViewScoped
public class UsuarioFormBean extends BaseBeanForm<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	String srcImage = "";
	
	
	public void uploadImage(FileUploadEvent event)
	{
		srcImage = UploadUtils.upload(event);
	}
	
	//Criptografa a senha
	@Override
	public String cadastrar(String entidade)
	{
		System.out.println(srcImage);
		this.getEntidade().setSenha(StringUtils.criptografarSenha(this.getEntidade().getSenha()));
		
		this.getEntidade().setSrcImagemPerfil(srcImage);

		return super.cadastrar(entidade);
	}
	
	/**
	public String logar()
	{
		System.out.println("[Login e verificação]");	
		if(!this.validarLoginSenha())
		{
			return null;
		}
		
		else
		{
			try
			{
				System.out.println("[Realizando o login] " + usuarioLogado.getCliente().getNome());
				
				SessionContext.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				
				return "/home.xhtml?faces-redirect=true";
			} 
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String logOut()
	{
		
		SessionContext.getInstance().encerrarSessao();
		
		return "/security/login.xhtml?faces-redirect=true";
	}
	**/
	/**private boolean validarLoginSenha()
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
				Usuario usuarioEncontrado = this.getDao().consultarPorParametros("t.login = '" + login + "'").get(0);
				
				if(!StringUtils.criptografarSenha(senha).equals(usuarioEncontrado.getSenha()))
				{
					System.out.println("Usuario ou senha incorretos!");
					
					return false;
				}
				
				else
				{
					// Define o usuario logado da sessão
					this.usuarioLogado = usuarioEncontrado;
				}
			}

			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}**/
}
