package br.com.snowbine.base.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.snowbine.base.dao.GenericDao;

public class BaseBean<T extends Serializable, D extends GenericDao<T>>
{
	private D dao;
	private T entidade;
	private List<T> listaEntidade;

	private Class<T> classeEntidade;
	private Class<D> classeDao;

	public BaseBean()
	{

	}

	@PostConstruct
	public void init()
	{
		// Encontra a classe
		classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		classeDao = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

		try
		{
			dao = classeDao.newInstance();
			entidade = classeEntidade.newInstance();

			this.listar();
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar()
	{
		try
		{
			Method metodoListar = dao.getClass().getMethod("listarTodos");
			this.setListaEntidade((List<T>) metodoListar.invoke(dao));

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return this.getListaEntidade();
	}

	public String cadastrar(String entidade)
	{
		try
		{
			Method salvar = dao.getClass().getMethod("salvar", Serializable.class);
			salvar.invoke(dao, this.entidade);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(entidade + " Salvo com sucesso"));
		}

		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ocorreu um erro ao salvar o registro!"));
			e.printStackTrace();
		}

		return "";
	}

	@SuppressWarnings("unchecked")
	public void editar(Integer id)
	{
		try
		{
			Method consultarPorId = dao.getClass().getMethod("consultarPorId", Integer.class);
			this.entidade = (T) consultarPorId.invoke(dao, id);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void onLoad(AjaxBehaviorEvent event)
	{
		System.out.println("oi");
	}
	
	public String listagem(String nomeEntidade)
	{
		return "list" + nomeEntidade;
	}

	public void setListaEntidade(List<T> lista)
	{
		this.listaEntidade = lista;
	}

	public List<T> getListaEntidade()
	{
		return listaEntidade;
	}

	public void setEntidade(T entidade)
	{
		this.entidade = entidade;
	}
	
	public T getEntidade()
	{
		return entidade;
	}

	public D getDao()
	{
		return dao;
	}
}
