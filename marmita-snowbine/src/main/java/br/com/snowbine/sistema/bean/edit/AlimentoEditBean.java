package br.com.snowbine.sistema.bean.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.edit.BaseBeanEdit;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.entity.Alimento;
import br.com.snowbine.sistema.entity.Endereco;

@Named
@ViewScoped
public class AlimentoEditBean extends BaseBeanEdit<Alimento, AlimentoDao> implements Serializable
{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Integer idEntidade = Integer.parseInt((String) session.getAttribute("idEntidade"));
		
		this.setEntidade(this.consultarPorId(idEntidade));
	}
}
