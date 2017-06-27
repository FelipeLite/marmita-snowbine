package br.com.snowbine.sistema.bean.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.edit.BaseBeanEdit;
import br.com.snowbine.sistema.dao.ClienteDao;
import br.com.snowbine.sistema.entity.Cliente;
import br.com.snowbine.sistema.entity.Endereco;

@Named
@ViewScoped
public class ClienteEditBean extends BaseBeanEdit<Cliente, ClienteDao> implements Serializable
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

	public ClienteEditBean()
	{
		endereco = new Endereco();
	}

	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Integer idEntidade = Integer.parseInt((String) session.getAttribute("idEntidade"));
		
		this.setEntidade(this.consultarPorId(idEntidade));
		this.setEndereco(this.getEntidade().getEndereco());
	}

	public String cadastrarCliente()
	{
		System.out.println(this.getEntidade().getSobrenome());
		this.getEntidade().setEndereco(endereco);
		super.cadastrar();

		return "";
	}
}
