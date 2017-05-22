package br.com.test.dao;

import br.com.snowbine.dao.CidadeDao;
import br.com.snowbine.dao.EstadoDao;
import br.com.snowbine.entity.Cidade;

public class TestPersistenceDao
{
	public static void main(String[] args)
	{
		Cidade e = new Cidade();
		CidadeDao dao = new CidadeDao();
		EstadoDao eDao = new EstadoDao();
		
		e.setNome("Laguna");
		e.setEstado(eDao.listarTodos().get(0));
		
		dao.salvar(e);
		
	}
}
