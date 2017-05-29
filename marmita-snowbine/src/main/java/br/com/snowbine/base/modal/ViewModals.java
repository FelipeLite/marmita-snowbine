package br.com.snowbine.base.modal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.context.RequestContext;

public class ViewModals
{
	public Object baseBean;
	
	// Abre os dialogs
	public void openDialog(String formSelecionada, Integer idEntidade)
	{
		 Map<String,Object> options = new HashMap<String, Object>();
		 
	     options.put("resizable", false);
	     options.put("draggable", false);
	     options.put("modal", true);
	     options.put("width", 500);
	     options.put("height", 500);
	     
	     String endereco = "/dialogs/dialog" + formSelecionada;
	     
	     RequestContext.getCurrentInstance().openDialog(endereco, options, null);
	     
	     generateInfo(idEntidade);
	     
	}
	
	// Pega as informações do objeto da lista
	public void generateInfo(Integer id)
	{
		System.out.println("eu");
		try
		{
			Method editar = baseBean.getClass().getMethod("editar", Integer.class);
			System.out.println(editar.getName());
			editar.invoke(baseBean, id);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
