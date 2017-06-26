package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.snowbine.sistema.entity.Cliente;
import br.com.snowbine.sistema.entity.Endereco;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.util.StringUtils;
import br.com.snowbine.sistema.util.UploadUtils;

@Named
@ViewScoped
public class CriarContaFormBean  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ClienteFormBean clienteFormBean;
	private UsuarioFormBean usuarioFormBean;
	private GrupoFormBean grupoBean;

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
	
	public CriarContaFormBean()
	{
		this.cliente = new Cliente();
		this.endereco = new Endereco();
		this.usuario = new Usuario();
		
		this.clienteFormBean = new ClienteFormBean();
		this.usuarioFormBean = new UsuarioFormBean();
		this.grupoBean = new GrupoFormBean();
	}
	
	public void criarConta()
	{
		System.out.println("[Criar conta]");
		
		//Seta os valores de endereço e usuário
		this.getCliente().setEndereco(this.getEndereco());
		this.getUsuario().setCliente(this.getCliente());
		
		//Seta os parametros base de usuário
		this.getUsuario().setAtivo(true);
		
		//Seta o grupo de clientes
		this.getUsuario().setGrupo(grupoBean.consultarPorId(2));
		
		//Seta a imagem de perfil
		this.getUsuario().setSrcImagemPerfil(srcImage);
		
		//Seta a senha criptografada
		this.getUsuario().setSenha(StringUtils.criptografarSenha(this.getUsuario().getSenha()));
		
	}
	
	public void uploadImage(FileUploadEvent event)
	{
		srcImage = UploadUtils.upload(event);
	}
	
}
