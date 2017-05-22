package br.com.snowbine.base.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.snowbine.base.dao.GenericDao;

public class BaseBean<T extends Serializable, D extends GenericDao<T>>
{
	private D dao;
	private T entidade;
	private List<T> listaEntidade;
	
	private Class<T> classeEntidade;
	private Class<D> classeDao;
	
	@SuppressWarnings("unchecked")
	public BaseBean()
	{
		//Encontra a classe
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
			this.setListaEntidade((List<T>)metodoListar.invoke(dao));
			
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return this.getListaEntidade();
	}
	
	public String cadastrar()
	{
		try
		{
			Method salvar = dao.getClass().getMethod("salvar", Serializable.class);
			salvar.invoke(dao, entidade);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public String editar(Integer id, String nomeEntidade)
	{
		try
		{
			Method consultarPorId = dao.getClass().getMethod("consultarPorId", Integer.class);
			this.entidade = (T)consultarPorId.invoke(dao, id);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "form" + nomeEntidade;
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
	
	public T getEntidade()
	{
		return entidade;
	}
}
