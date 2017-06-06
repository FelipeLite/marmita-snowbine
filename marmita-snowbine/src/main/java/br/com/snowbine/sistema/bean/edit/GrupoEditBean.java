package br.com.snowbine.sistema.bean.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.edit.BaseBeanEdit;
import br.com.snowbine.sistema.bean.list.GrupoListBean;
import br.com.snowbine.sistema.dao.GrupoDao;
import br.com.snowbine.sistema.entity.Grupo;

@Named
@ViewScoped
public class GrupoEditBean extends BaseBeanEdit<Grupo, GrupoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public GrupoEditBean()
	{
	}
	
	@Override
	public void cadastrar()
	{
		super.cadastrar();
	}
	
	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Integer idEntidade = Integer.parseInt((String) session.getAttribute("idEntidade"));
		this.setEntidade(this.consultarPorId(idEntidade));		
	}
}
