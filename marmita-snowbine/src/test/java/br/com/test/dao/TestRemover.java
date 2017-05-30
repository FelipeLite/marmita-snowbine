package br.com.test.dao;

import br.com.snowbine.sistema.dao.GrupoDao;
import br.com.snowbine.sistema.entity.Grupo;

public class TestRemover
{
	public static void main(String[] args)
	{
		GrupoDao dao = new GrupoDao();
		
		try
		{
			Grupo g = dao.consultarPorId(9);
			System.out.println(g.getNome());
			dao.excluir(g);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
