package br.com.snowbine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.snowbine.base.entity.BaseEntity;

@Entity
@Table(name = "alimento")
public class Alimento implements Serializable, BaseEntity
{
	private static final long serialVersionUID = 1953870614966269314L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "categoria", nullable = false)
	private String categoria;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "preco_kg", nullable = false)
	private Double precoKg;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getCategoria()
	{
		return categoria;
	}

	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Double getPrecoKg()
	{
		return precoKg;
	}

	public void setPrecoKg(Double precoKg)
	{
		this.precoKg = precoKg;
	}

	public Alimento()
	{

	}

}
