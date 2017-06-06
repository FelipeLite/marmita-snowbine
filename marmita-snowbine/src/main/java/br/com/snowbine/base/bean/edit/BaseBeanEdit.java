package br.com.snowbine.base.bean.edit;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.snowbine.base.dao.GenericDao;

public abstract class BaseBeanEdit<T extends Serializable, D extends GenericDao<T>>
{
	private D dao;
	private T entidade;

	private Class<T> classeEntidade;
	private Class<D> classeDao;

	@SuppressWarnings("unchecked")
	public BaseBeanEdit()
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


	public void cadastrar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			Method salvar = dao.getClass().getMethod("salvar", Serializable.class);
			salvar.invoke(dao, getEntidade());
			context.addMessage(null, new FacesMessage("Registro Atualizado com sucesso"));
			
			this.entidade = null;
			
			RequestContext.getCurrentInstance().closeDialog(null);
			
		}

		catch (Exception e)
		{
			FacesMessage mensagem = new FacesMessage("Ocorreu um erro ao atualizar o registro!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);

			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public T consultarPorId(Integer id)
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

		return entidade;

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
