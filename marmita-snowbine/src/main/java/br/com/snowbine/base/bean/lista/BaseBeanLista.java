package br.com.snowbine.base.bean.lista;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.snowbine.base.dao.GenericDao;

public class BaseBeanLista<T extends Serializable, D extends GenericDao<T>>
{
	private D dao;
	private T entidade;
	
	private List<T> listaEntidade;

	private Class<T> classeEntidade;
	private Class<D> classeDao;

	
	@SuppressWarnings("unchecked")
	public BaseBeanLista()
	{
		// Encontra a classe
		classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		classeDao = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

		try
		{
			dao = classeDao.newInstance();
			entidade = classeEntidade.newInstance();
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
	
	@SuppressWarnings("unchecked")
	public T consultarPorId(Integer id)
	{
		try
		{
			Method consultarPorId = dao.getClass().getMethod("consultarPorId", Integer.class);
			T entidadeRetornar = (T) consultarPorId.invoke(dao, id);
			
			return entidadeRetornar;
		}

		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String excluir()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		try
		{
			System.out.println(this.getEntidade());
			dao.excluir(this.entidade);
			
			context.addMessage(null, new FacesMessage("Registro excluído com sucesso!"));
			
			this.listar();
			
			return "";
		} 
		
		catch (Exception e)
		{
			FacesMessage mensagem = new FacesMessage("Ocorreu um erro ao realizar a exclusão do registro!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			
			return null;

		}
		
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
