package br.com.snowbine.sistema.persistence;

import javax.persistence.Persistence;

public class GenerateTables
{
	public static void main(String[] args)
	{
		Persistence.createEntityManagerFactory("SnowbinePU");
	}
}
