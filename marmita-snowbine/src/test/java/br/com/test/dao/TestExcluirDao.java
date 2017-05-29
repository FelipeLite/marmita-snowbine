package br.com.test.dao;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import br.com.snowbine.sistema.dao.EstadoDao;

public class TestExcluirDao
{
	public static void main(String[] args)
	{
		EstadoDao dao = new EstadoDao();
		
		try
		{
			Method[] metodos = dao.getClass().getMethods();
			
			for(Method m: metodos)
			{
				if(m.getName().equals("salvar"))
				{
					System.out.println(m.getName());
					Parameter[] parametros = m.getParameters();
					
					for(Parameter p : parametros)
					{
						System.out.println(p.getType().getName());
					}
				}
			}
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
