package br.com.snowbine.sistema.bean.list;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.MarmitaDao;
import br.com.snowbine.sistema.dao.VendaDao;
import br.com.snowbine.sistema.entity.Marmita;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.entity.Venda;

@Named
@ViewScoped
public class MarmitaClienteListBean extends BaseBeanLista<Marmita, MarmitaDao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private VendaDao vendaDao;
	private MarmitaDao marmitaDao;
	
	
	public MarmitaClienteListBean()
	{
		vendaDao = new VendaDao();
		marmitaDao = new MarmitaDao();
	}
	
	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		// Vai listar pelo id do cliente
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioLogado");
		super.listarPorParametros("t.cliente.id = " + usuarioAutenticado.getCliente().getId());
		;
	}

	public String comprar(Integer id)
	{
		System.out.println("[COMPRANDO MARMITA NA LISTA]");
		Marmita m = null;

		try
		{
			m = marmitaDao.consultarPorId(id);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//Como já vái montar e comprar, será basicamente uma compra débito
		Date dataAtual = new Date();
		
		// Monta um objeto da venda
		Venda v = new Venda();

		// Como é débito, os vencimentos vão ser na data atual
		v.setDataCriacao(dataAtual);
		v.setDataPagamento(dataAtual);
		v.setDataVencimento(dataAtual);
		v.setValor(m.getPreco());
		v.setCliente(m.getCliente());
		v.setValorPago(m.getPreco());

		// Adiciona a marmita na venda
		v.getMarmitas().add(m);
		
		try
		{
			System.out.println("Fazendo a venda");
			//Faz a venda
			vendaDao.salvar(v);
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage("Venda realizada com sucesso!");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);	
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}
}
