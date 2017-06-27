package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.MarmitaDao;
import br.com.snowbine.sistema.dao.VendaDao;
import br.com.snowbine.sistema.entity.Alimento;
import br.com.snowbine.sistema.entity.Marmita;
import br.com.snowbine.sistema.entity.Usuario;
import br.com.snowbine.sistema.entity.Venda;

@Named
@ViewScoped
public class MarmitaFormBean extends BaseBeanForm<Marmita, MarmitaDao> implements Serializable
{

	private static final long serialVersionUID = 1L;

	private List<Alimento> alimentos;
	private Double preco;
	private Usuario usuarioAutenticado;
	
	private VendaDao vendaDao;
	
	public Usuario getUsuarioAutenticado()
	{
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado)
	{
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public void setPreco(Double preco)
	{
		this.preco = preco;
	}
	
	public Double getPreco()
	{
		return this.preco;
	}
	public MarmitaFormBean()
	{
		alimentos = new ArrayList<Alimento>();
		preco = 0.0;
		
		vendaDao = new VendaDao();
	}

	@PostConstruct
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		//Pega o usuario autenticado na sessão
		this.setUsuarioAutenticado((Usuario)session.getAttribute("usuarioLogado"));
	}

	public String cadastrarMarmita()
	{
		return "";
	}

	public List<Alimento> getAlimentos()
	{
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos)
	{
		this.alimentos = alimentos;
	}
	
	public String montarMarmita()
	{
		boolean validarPedido = true;
		
		//Pega o usuário autenticado na sessão
		Double peso = 0.0;
		
		for(Alimento a : this.getAlimentos())
		{
			if(a.getQuantidade() == null || a.getQuantidade() <= 0)
			{
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage("Preencha todas as quantidades dos itens selecionados!!");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);	
				validarPedido = false;
				
				break;
			}
			preco += a.getQuantidade() * a.getPrecoKg();
			peso += a.getQuantidade();
			this.getEntidade().getAlimentos().add(a);
		}
		
		if(validarPedido)
		{
			//Define peso e preço da marmita
			this.getEntidade().setPeso(peso);
			this.getEntidade().setPreco(this.preco);
			
			//Define o cliente da marmita
			this.getEntidade().setCliente(this.getUsuarioAutenticado().getCliente());
			this.cadastrarMarmita();
		}
		
		return "";
	}
	
	public String comprarMarmita()
	{
		//Persiste a marmita
		this.cadastrar("Marmita");
		
		//Como já vái montar e comprar, será basicamente uma compra débito
		Date dataAtual = new Date();
		
		//Monta um objeto da venda
		Venda v = new Venda();
		
		//Como é débito, os vencimentos vão ser na data atual
		v.setDataCriacao(dataAtual);
		v.setDataPagamento(dataAtual);
		v.setDataVencimento(dataAtual);
		v.setValor(this.getEntidade().getPreco());
		v.setCliente(this.getUsuarioAutenticado().getCliente());
		v.setValorPago(this.getEntidade().getPreco());
		
		//Adiciona a marmita na venda
		v.getMarmitas().add(this.getEntidade());
		
		try
		{
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
