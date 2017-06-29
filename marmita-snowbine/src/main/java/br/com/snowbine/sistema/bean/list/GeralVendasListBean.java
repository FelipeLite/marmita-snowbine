package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.VendaDao;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.entity.Venda;

@Named
@ViewScoped
public class GeralVendasListBean extends BaseBeanLista<Venda, VendaDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	

	@PostConstruct
	public void init()
	{
		super.listar();

	}
}
