package br.com.snowbine.sistema.bean.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import br.com.snowbine.base.bean.edit.BaseBeanEdit;
import br.com.snowbine.sistema.dao.UsuarioDao;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.StringUtils;
import br.com.snowbine.sistema.util.UploadUtils;

@Named
@SessionScoped
public class UsuarioEditBean extends BaseBeanEdit<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	String srcImage = "";
	
	
	public void uploadImage(FileUploadEvent event)
	{
		srcImage = UploadUtils.upload(event);
	}
	
	//Criptografa a senha
	@Override
	public void cadastrar()
	{
		this.getEntidade().setSenha(StringUtils.criptografarSenha(this.getEntidade().getSenha()));

		if(!this.srcImage.equals(""))
		{
			this.getEntidade().setSrcImagemPerfil(srcImage);
		}

	}
	
	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Integer idEntidade = Integer.parseInt((String) session.getAttribute("idEntidade"));
		this.setEntidade(this.consultarPorId(idEntidade));		
	}
	
	
}
