package br.com.snowbine.sistema.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.snowbine.base.entity.BaseEntity;

@Entity
@Table(name = "marmita")
public class Marmita implements Serializable, BaseEntity
{
	private static final long serialVersionUID = 4142734813807832783L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "peso", nullable = false)
	private Double peso;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="item_marmita", joinColumns = {@JoinColumn(name = "id_marmita")}, inverseJoinColumns = {@JoinColumn(name = "id_alimento")})
	private Set<Alimento> alimentos = new HashSet<Alimento>(0);
	
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

	public Double getPreco()
	{
		return preco;
	}

	public void setPreco(Double preco)
	{
		this.preco = preco;
	}

	public Double getPeso()
	{
		return peso;
	}

	public void setPeso(Double peso)
	{
		this.peso = peso;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public Set<Alimento> getAlimentos()
	{
		return alimentos;
	}

	public void setAlimentos(Set<Alimento> alimentos)
	{
		this.alimentos = alimentos;
	}

	public Marmita()
	{
		
	}
}
