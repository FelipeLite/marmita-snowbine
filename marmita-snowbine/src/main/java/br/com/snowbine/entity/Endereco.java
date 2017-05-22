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
@Table(name = "endereco")
public class Endereco implements Serializable, BaseEntity
{
	private static final long serialVersionUID = -4207710165799399234L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "rua", nullable = false)
	private String rua;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Column(name = "complemento")
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade", nullable = false)
	private Cidade cidade;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getRua()
	{
		return rua;
	}

	public void setRua(String rua)
	{
		this.rua = rua;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}
	
	public String getComplemento()
	{
		return complemento;
	}

	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	public Cidade getCidade()
	{
		return cidade;
	}

	public void setCidade(Cidade cidade)
	{
		this.cidade = cidade;
	}

	public Endereco()
	{
		
	}

}
