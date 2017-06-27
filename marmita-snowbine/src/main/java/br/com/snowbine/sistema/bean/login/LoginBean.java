package br.com.snowbine.sistema.bean.login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.UsuarioDao;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.EmailUtils;
import br.com.snowbine.sistema.util.LoginUtils;
import br.com.snowbine.sistema.util.SessionUtil;
import br.com.snowbine.sistema.util.StringUtils;

@Named
@SessionScoped
public class LoginBean extends BaseBeanForm<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static Usuario usuarioLogado;
	
	public static Usuario getUsuarioLogado()
	{
		return LoginBean.usuarioLogado;
	}

	public String logar()
	{
		if(!this.validarLoginSenha())
		{
			return null;
		}
		
		else
		{
			SessionUtil.setParam("usuarioLogado", usuarioLogado);
			
			return "/views/index.xhtml?faces-redirect=true";
		}
		
	}
	
	
	private boolean validarLoginSenha()
	{
		String login = this.getEntidade().getLogin().trim();
		String senha = this.getEntidade().getSenha().trim();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try
		{
			//Procura pelo usuario
			Integer resultado = this.getDao().consultarPorParametros("t.login = '" + login + "'").size();

			if(resultado == 0)
			{
				FacesMessage mensagem = new FacesMessage("Usuário não encontrado!");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);	
				
				return false;
			}
			
			else
			{
				//Encontra o usuario e compara a senha
				Usuario usuarioEncontrado = this.getDao().consultarPorParametros("t.login = '" + login + "'").get(0);
				
				//Verifica se o usuário não está inativo
				if(!usuarioEncontrado.isAtivo())
				{
					FacesMessage mensagem = new FacesMessage("Usuário inativo!");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, mensagem);	
					
					return false;
				}
				
				if(!StringUtils.criptografarSenha(senha).equals(usuarioEncontrado.getSenha()))
				{
					FacesMessage mensagem = new FacesMessage("Usuário ou senha incorretos!");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, mensagem);	
					
					return false;
				}
				
				else
				{
					// Define o usuario logado da sessão
					LoginBean.usuarioLogado = usuarioEncontrado;
				}
			}

			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public String logout() 
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		LoginBean.usuarioLogado = null;
		return "/views/login.xhtml?faces-redirect=true";
	}
	
	public void recuperarSenha()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		String login = this.getEntidade().getLogin().trim();
		
		//Verifica se o usuario não é nulo ou vazio
		if(login == null || login.equals(""))
		{
			FacesMessage mensagem = new FacesMessage("Usuário não selecionado!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);	
		}
		
		//Verifica se o usuario existe
		try
		{
			Usuario u = this.getDao().consultarPorParametros("t.login = '" + login + "'").get(0);
			System.out.println("[Usuario encontrado] " + u.getLogin());
			
			//Gera uma nova senha
			String novaSenha = LoginUtils.gerarNovaSenha();
			System.out.println(novaSenha + " [nova senha]");
			
			//Salva a nova senha criptografada
			u.setSenha(StringUtils.criptografarSenha(novaSenha));
			this.getDao().salvar(u);
			
			//Envia por email a nova senha do usuario
			EmailUtils.sendEmail(u.getEmail(), u.getLogin(), novaSenha);
			
			FacesMessage mensagem = new FacesMessage("Uma nova senha foi enviada para o email cadastrado.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);	
			
		} 
		
		catch (Exception e)
		{
			//Se der exception informa que o usuario não foi encontrado
			FacesMessage mensagem = new FacesMessage("Usuário não encontrado!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);	
		}
	}
}
