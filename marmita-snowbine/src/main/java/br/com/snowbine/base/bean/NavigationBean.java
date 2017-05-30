package br.com.snowbine.base.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class NavigationBean
{
	public void openDialog()
	{
	     RequestContext context = RequestContext.getCurrentInstance();
	    // context.execute("myDialog.show()");
	     //context.update("myDialog");
	}
}
