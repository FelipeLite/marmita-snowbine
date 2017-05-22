package br.com.snowbine.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.snowbine.persistence.PersistenceUtils;
import br.com.snowbine.util.HibernateUtils;

public class GenericDao<T extends Serializable>
{
	private Class<T> classe;
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public GenericDao()
	{
		classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public EntityManager getEntityManager()
	{
		//Cria o entity Manager
		entityManager = PersistenceUtils.getEntityManager().createEntityManager();
		
		return entityManager;
	}

	public Class<T> getEntityClass()
	{
		return classe;
	}
	
	public void salvar(T entidade)
	{
		Session sessao = HibernateUtils.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try
		{
			t = sessao.beginTransaction();
			sessao.saveOrUpdate(entidade);
			t.commit();
		}
		catch (Exception e)
		{
			if (t != null)
			{
				t.rollback();
			}
			throw (e);
		}
		finally
		{
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos()
	{
		Session sessao = HibernateUtils.getFabricaDeSessoes().openSession();
		try
		{
			Criteria consulta = sessao.createCriteria(classe);
			List<T> resultado = consulta.list();
			return resultado;
		}
		catch (Exception e)
		{
			throw (e);
		}
		finally
		{
			sessao.close();
		}
	}
	
	public void excluir(T objeto) throws Exception
	{
		Session sessao = HibernateUtils.getFabricaDeSessoes().openSession();
		try
		{
			sessao.delete(objeto);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public T consultarPorId(Integer id) throws Exception
	{
		Session sessao = HibernateUtils.getFabricaDeSessoes().openSession();
		
		try
		{
			return (T) sessao.get(classe, id);
		}
		catch (NoResultException e)
		{
			return null;
		}
		catch (NonUniqueResultException e1)
		{
			throw new Exception("A consulta por id retornou mais de um resultado!!");
		}
		
		finally
		{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> consultarPorParametros(String parametros) throws Exception
	{
		Session sessao = HibernateUtils.getFabricaDeSessoes().openSession();
		
		if (parametros.trim().length() > 0)
		{
			parametros = " where " + parametros;
		}

		String hql = "from " + getEntityClass().getName() + " t" + parametros;
		
		System.out.println(hql);
		
		org.hibernate.Query query = sessao.createQuery(hql);
		
		try
		{
			return (List<T>) query.list();
			
		}
		catch (NoResultException e)
		{
			return null;
		}
		catch (NonUniqueResultException e1)
		{
			return null;
		}
		
		finally
		{
			sessao.close();
		}
	}

}
