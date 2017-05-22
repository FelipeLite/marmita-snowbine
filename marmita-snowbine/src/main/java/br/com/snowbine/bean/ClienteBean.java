package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.ClienteDao;
import br.com.snowbine.entity.Cliente;
import br.com.snowbine.entity.Endereco;

@ManagedBean
@SessionScoped
public class ClienteBean extends BaseBean<Cliente, ClienteDao> implements Serializable
{
	private static final long serialVersionUID = 3475832666657941309L;
	
	public String cadastrarCliente(Endereco endereco)
	{
		this.getEntidade().setEndereco(endereco);
		super.cadastrar();

		return "";
	}
}
