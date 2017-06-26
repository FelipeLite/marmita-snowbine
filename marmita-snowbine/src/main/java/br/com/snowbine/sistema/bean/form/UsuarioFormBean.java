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
}
