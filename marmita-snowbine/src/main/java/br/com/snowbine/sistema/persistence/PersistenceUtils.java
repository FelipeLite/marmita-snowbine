package br.com.snowbine.sistema.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class PersistenceUtils
{
	@Produces
	@RequestScoped
	public static EntityManagerFactory getEntityManager()
	{
		return Persistence.createEntityManagerFactory("SnowbinePU");
	}
}
