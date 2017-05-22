package br.com.snowbine.persistence;

import javax.persistence.Persistence;

public class GenerateTables
{
	public static void main(String[] args)
	{
		Persistence.createEntityManagerFactory("SnowbinePU");
	}
}
