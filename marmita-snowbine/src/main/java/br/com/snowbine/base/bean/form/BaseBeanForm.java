package br.com.snowbine.base.bean.form;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.snowbine.base.dao.GenericDao;

public class BaseBeanForm<T extends Serializable, D extends GenericDao<T>>
{
	private D dao;
	private T entidade;

	private Class<T> classeEntidade;
	private Class<D> classeDao;

	@SuppressWarnings("unchecked")
	public BaseBeanForm()
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


	public String cadastrar(String entidade)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			Method salvar = dao.getClass().getMethod("salvar", Serializable.class);
			salvar.invoke(dao, this.entidade);
			context.addMessage(null, new FacesMessage("Registro salvo com sucesso"));
			
			this.entidade = null;
		}

		catch (Exception e)
		{
			FacesMessage mensagem = new FacesMessage("Ocorreu um erro ao inserir o registro!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);

			e.printStackTrace();
		}

		return "";
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
