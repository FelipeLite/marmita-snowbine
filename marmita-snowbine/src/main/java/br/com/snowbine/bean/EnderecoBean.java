package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.EnderecoDao;
import br.com.snowbine.entity.Endereco;

@ManagedBean
@SessionScoped
public class EnderecoBean extends BaseBean<Endereco, EnderecoDao> implements Serializable
{
	private static final long serialVersionUID = -4812922714060518329L;

	public ActionListener action()
	{
		this.cadastrar();
	    return new ActionListener() 
	    {
	        @Override
	        public void processAction(ActionEvent event) throws AbortProcessingException 
	        {
	            
	        }
	    };
	}
}
