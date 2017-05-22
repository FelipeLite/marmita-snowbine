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
@Table(name = "Cliente")
public class Cliente implements Serializable, BaseEntity
{
	private static final long serialVersionUID = -6628616189655410348L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "idade", nullable = false)
	private Integer idade;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "rg", nullable = false)
	private String rg;

	@Column(name = "sexo", nullable = false)
	private char sexo;

	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSobrenome()
	{
		return sobrenome;
	}

	public void setSobrenome(String sobrenome)
	{
		this.sobrenome = sobrenome;
	}

	public Integer getIdade()
	{
		return idade;
	}

	public void setIdade(Integer idade)
	{
		this.idade = idade;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public String getRg()
	{
		return rg;
	}

	public void setRg(String rg)
	{
		this.rg = rg;
	}

	public char getSexo()
	{
		return sexo;
	}

	public void setSexo(char sexo)
	{
		this.sexo = sexo;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public Cliente()
	{
		
	}
}
