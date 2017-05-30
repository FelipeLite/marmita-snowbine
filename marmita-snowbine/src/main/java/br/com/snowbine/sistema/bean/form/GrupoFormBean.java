package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.GrupoDao;
import br.com.snowbine.sistema.entity.Grupo;

@Named
@ViewScoped
public class GrupoFormBean extends BaseBeanForm<Grupo, GrupoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init()
	{
		System.out.println("eu");
	}
}
