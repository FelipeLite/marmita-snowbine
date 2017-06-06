package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.entity.Alimento;

@Named
@ViewScoped
public class AlimentoListBean extends BaseBeanLista<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		super.listar();
	}
	
	public Alimento[] listarAlimentosPorCategoria(Long categoria) throws Exception
	{
		List<Alimento> alimentosLista = null;
		
		if (categoria == 1)
		{
			alimentosLista= this.getDao().consultarPorParametros("categoria = 'Principal'");
			
		}
		else if (categoria == 2)
		{
			alimentosLista= this.getDao().consultarPorParametros("categoria = 'Acompanhamento'");
		}
		else if (categoria == 3)
		{
			alimentosLista= this.getDao().consultarPorParametros("categoria = 'Sobremesa'");
		}
		else if (categoria == 4)
		{
			alimentosLista= this.getDao().consultarPorParametros("categoria = 'Bebida'");
		}
		else
		{
			alimentosLista= this.getDao().listarTodos();
		}
		
		Alimento[] alimentosArray = new Alimento[alimentosLista.size()];
		for (int i=0; i<alimentosLista.size(); i++)
		{
			alimentosArray[i] = alimentosLista.get(i);
		}
		
		return alimentosArray;
	}
	
}
