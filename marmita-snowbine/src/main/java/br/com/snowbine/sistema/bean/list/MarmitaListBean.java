package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.MarmitaDao;
import br.com.snowbine.sistema.entity.Marmita;

@Named
@ViewScoped
public class MarmitaListBean extends BaseBeanLista<Marmita, MarmitaDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		super.listar();
	}
}
