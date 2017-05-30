package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.ClienteDao;
import br.com.snowbine.sistema.entity.Cliente;
import br.com.snowbine.sistema.entity.Endereco;

@Named
@ViewScoped
public class ClienteFormBean extends BaseBeanForm<Cliente, ClienteDao> implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Endereco endereco;

	public Endereco getEndereco()
	{
		return this.endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public ClienteFormBean()
	{
		endereco = new Endereco();
	}

	@PostConstruct
	public void init()
	{
		System.out.println(" id " + id);
	}

	public String cadastrarCliente()
	{
		this.getEntidade().setEndereco(endereco);

		super.cadastrar("Cliente");

		return "";
	}
}
