package br.com.snowbine.sistema.bean.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import br.com.snowbine.base.bean.edit.BaseBeanEdit;
import br.com.snowbine.sistema.bean.login.LoginBean;
import br.com.snowbine.sistema.dao.UsuarioDao;
import br.com.snowbine.sistema.entity.Cliente;
import br.com.snowbine.sistema.entity.Endereco;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.SessionUtil;
import br.com.snowbine.sistema.util.StringUtils;
import br.com.snowbine.sistema.util.UploadUtils;

@Named
@ViewScoped
public class MeuPerfilEditBean extends BaseBeanEdit<Usuario,UsuarioDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ClienteEditBean clienteEditBean;
	private UsuarioEditBean usuarioEditBean;

	private Endereco endereco;
	private Usuario usuario;
	private Cliente cliente;

	String srcImage = "";

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public MeuPerfilEditBean()
	{
		this.cliente = new Cliente();
		this.endereco = new Endereco();
		this.usuario = new Usuario();

		this.clienteEditBean = new ClienteEditBean();
		this.usuarioEditBean = new UsuarioEditBean();
	}

	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		this.setEntidade((Usuario)SessionUtil.getParam("usuarioLogado"));
		System.out.println(this.getEntidade().getId());

		this.usuario = this.getEntidade();
		this.cliente = usuario.getCliente();
		this.endereco = cliente.getEndereco();
	}

	public void uploadImage(FileUploadEvent event)
	{
		srcImage = UploadUtils.upload(event);
	}

	public void atualizarConta()
	{
		//Seta os valores de endereço e usuário
		this.cliente.setEndereco(this.getEndereco());
		this.usuario.setCliente(this.getCliente());

		//Seta a imagem de perfil
		if(srcImage != null && !srcImage.equals(""))
		{
			this.usuario.setSrcImagemPerfil(srcImage);
		}

		//Seta a senha criptografada
		this.usuario.setSenha(StringUtils.criptografarSenha(this.getUsuario().getSenha()));

		clienteEditBean.getDao().salvar(this.cliente);
		usuarioEditBean.getDao().salvar(this.usuario);
		
		super.cadastrar();
	}
	
	public String desativarConta()
	{
		this.getUsuario().setSenha(StringUtils.criptografarSenha(this.getUsuario().getSenha()));
		this.getUsuario().setAtivo(false);
		
		usuarioEditBean.getDao().salvar(this.getUsuario());
		
		super.cadastrar();
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		LoginBean.usuarioLogado = null;
		return "/views/login.xhtml?faces-redirect=true";
	}
}
