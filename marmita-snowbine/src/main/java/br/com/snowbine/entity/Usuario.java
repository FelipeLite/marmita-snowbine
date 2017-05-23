package br.com.snowbine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.snowbine.base.entity.BaseEntity;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, BaseEntity
{

	private static final long serialVersionUID = 6882440792609339991L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "ativo", nullable = false)
	private boolean ativo;

	@Column(name = "email", nullable = true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "id_grupo", nullable = false)
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	private Cliente cliente;
	
	@Column(name = "src_imagem_perfil", nullable = true)
	private String srcImagemPerfil;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public boolean isAtivo()
	{
		return ativo;
	}

	public void setAtivo(boolean ativo)
	{
		this.ativo = ativo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Grupo getGrupo()
	{
		return grupo;
	}

	public void setGrupo(Grupo grupo)
	{
		this.grupo = grupo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}
	
	public void setSrcImagemPerfil(String srcImagemPerfil)
	{
		this.srcImagemPerfil = srcImagemPerfil;
	}
	
	public String getSrcImagemPerfil()
	{
		return srcImagemPerfil;
	}

	public Usuario()
	{

	}

}
