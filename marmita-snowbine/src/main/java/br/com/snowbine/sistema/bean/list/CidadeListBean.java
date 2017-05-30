package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.CidadeDao;
import br.com.snowbine.sistema.entity.Cidade;

@Named
@ViewScoped
public class CidadeListBean extends BaseBeanLista<Cidade, CidadeDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		super.listar();
	}
	
	
}
