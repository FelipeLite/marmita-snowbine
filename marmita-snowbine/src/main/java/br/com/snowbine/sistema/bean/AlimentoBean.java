package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.lista.BaseBeanLista;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.entity.Alimento;

@ManagedBean
@SessionScoped
public class AlimentoBean extends BaseBeanLista<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1327394867498600287L;
}
