package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.entity.Alimento;

@Named
@SessionScoped
public class AlimentosMarmitaListBean extends BaseBeanLista<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer idEntidade;
	
	public Integer getIdEntidade()
	{
		return idEntidade;
	}

	public void setIdEntidade(Integer idEntidade)
	{
		this.idEntidade = idEntidade;
	}

	public void openDialog(Integer idEntidade)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		session.setAttribute("idEntidade", idEntidade.toString());
		
		Map<String,Object> options = new HashMap<String, Object>();
		 
	    options.put("resizable", true);
	    options.put("draggable", true);
	    options.put("modal", true);
	    options.put("width", "700px");
	    options.put("height", "600px");
	    options.put("contentWidth", "100%");
	    options.put("contentHeight", "90%");
	    options.put("size", "auto");
	     
	     String endereco = "/views/alimentosMarmita.xhtml";
	     
	     RequestContext.getCurrentInstance().openDialog(endereco, options, null);	     
	}
	
}
