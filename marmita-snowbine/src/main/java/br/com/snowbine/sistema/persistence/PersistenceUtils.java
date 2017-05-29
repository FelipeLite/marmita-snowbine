package br.com.snowbine.sistema.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class PersistenceUtils
{
	public static EntityManagerFactory getEntityManager()
	{
		return Persistence.createEntityManagerFactory("SnowbinePU");
	}
}
