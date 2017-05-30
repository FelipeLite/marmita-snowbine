package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.GrupoDao;
import br.com.snowbine.sistema.entity.Grupo;

@Named
@ViewScoped
public class GrupoListBean extends BaseBeanLista<Grupo, GrupoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		super.listar();
	}
}
