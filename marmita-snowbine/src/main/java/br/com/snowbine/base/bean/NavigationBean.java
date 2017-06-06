package br.com.snowbine.base.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class NavigationBean
{
	public Object entidade;
	private Integer idEntidade;
	
	public Integer getIdEntidade()
	{
		return idEntidade;
	}

	public void setIdEntidade(Integer idEntidade)
	{
		this.idEntidade = idEntidade;
	}

	public void openDialog(String formSelecionada, Integer idEntidade)
	{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		session.setAttribute("entidade", "Grupo");
		session.setAttribute("idEntidade", idEntidade.toString());
		
		Map<String,Object> options = new HashMap<String, Object>();
		 
	    options.put("resizable", true);
	    options.put("draggable", false);
	    options.put("modal", true);
	    options.put("width", 500);
	    options.put("height", 500);
	     
	     String endereco = "/dialogs/dialog" + formSelecionada;
	     
	     RequestContext.getCurrentInstance().openDialog(endereco, options, null);	     
	}
}
