package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.dao.MarmitaDao;
import br.com.snowbine.sistema.entity.Alimento;
import br.com.snowbine.sistema.entity.Marmita;

@Named
@SessionScoped
public class AlimentosMarmitaViewBean extends BaseBeanLista<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private MarmitaDao marmitaDao;
	
	private List<Alimento> alimentos;
	
	public MarmitaDao getMarmitaDao()
	{
		return marmitaDao;
	}

	public void setMarmitaDao(MarmitaDao marmitaDao)
	{
		this.marmitaDao = marmitaDao;
	}

	public List<Alimento> getAlimentos()
	{
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos)
	{
		this.alimentos = alimentos;
	}

	public AlimentosMarmitaViewBean()
	{
		marmitaDao = new MarmitaDao();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Integer idEntidade = Integer.parseInt((String) session.getAttribute("idEntidade"));
		
		System.out.println("INIT MARMITA");
		try
		{
			Marmita m = marmitaDao.consultarPorId(idEntidade);
			
			List<Alimento> alimentos = new ArrayList<Alimento>();
			for(Alimento a : m.getAlimentos())
			{	
				alimentos.add(a);
			}
			
			this.setAlimentos(alimentos);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init()
	{
		System.err.println("teste");
	}
	
}
