package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.sistema.dao.ClienteDao;
import br.com.snowbine.sistema.entity.Cliente;
import br.com.snowbine.sistema.entity.Endereco;

@ManagedBean
@ViewScoped
public class ClienteBean extends BaseBean<Cliente, ClienteDao> implements Serializable
{
	private static final long serialVersionUID = 3475832666657941309L;
	
	//Endereco para salvar junto com cliente
	public Endereco endereco = new Endereco();
	
	public Endereco getEndereco()
	{
		return this.endereco;
	}
	
	public String cadastrarCliente()
	{
		
		this.getEntidade().setEndereco(endereco);
		
		super.cadastrar("Cliente");

		return "";
	}
}
