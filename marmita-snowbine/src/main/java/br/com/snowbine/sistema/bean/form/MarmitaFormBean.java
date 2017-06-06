package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.MarmitaDao;
import br.com.snowbine.sistema.entity.Marmita;
import br.com.snowbine.sistema.entity.Alimento;
import br.com.snowbine.sistema.entity.Cliente;

@Named
@ViewScoped
public class MarmitaFormBean extends BaseBeanForm<Marmita, MarmitaDao> implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Cliente cliente;
	private Set<Alimento> alimentos = new HashSet<Alimento>(0);

	public Cliente getCliente()
	{
		return this.cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public MarmitaFormBean()
	{
		cliente = new Cliente();
	}

	@PostConstruct
	public void init()
	{
		System.out.println(" id " + id);
	}

	public String cadastrarMarmita()
	{
		this.getEntidade().setCliente(cliente);
		
		this.getEntidade().setAlimentos(alimentos);

		super.cadastrar("Marmita");

		return "";
	}
}
