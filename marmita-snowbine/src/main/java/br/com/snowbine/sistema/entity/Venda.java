package br.com.snowbine.sistema.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import br.com.snowbine.base.entity.BaseEntity;

@Entity
@Table(name = "venda")
public class Venda implements Serializable, BaseEntity
{
	private static final long serialVersionUID = 330205535748272330L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "data_criacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(name = "data_vencimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Column(name = "data_pagamento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	@Column(name = "valor_pago", nullable = true)
	private Double valorPago;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinTable(name="item_venda", joinColumns = {@JoinColumn(name = "id_venda")}, inverseJoinColumns = {@JoinColumn(name = "id_marmita")})
	private Set<Marmita> marmitas = new HashSet<Marmita>(0);
	
	@Transient
	private List<Marmita> marmitasTransient;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao)
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento()
	{
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento)
	{
		this.dataPagamento = dataPagamento;
	}

	public Double getValorPago()
	{
		return valorPago;
	}

	public void setValorPago(Double valorPago)
	{
		this.valorPago = valorPago;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public Set<Marmita> getMarmitas()
	{
		return marmitas;
	}

	public void setMarmitas(Set<Marmita> marmitas)
	{
		this.marmitas = marmitas;
	}
	

	public List<Marmita> getMarmitasTransient()
	{
		return marmitasTransient;
	}

	public void setMarmitasTransient(List<Marmita> marmitasTransient)
	{
		this.marmitasTransient = marmitasTransient;
	}

	public Venda()
	{
		
	}
}
