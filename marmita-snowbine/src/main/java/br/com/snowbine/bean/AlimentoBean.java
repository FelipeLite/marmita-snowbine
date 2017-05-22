package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.AlimentoDao;
import br.com.snowbine.entity.Alimento;

@ManagedBean
@SessionScoped
public class AlimentoBean extends BaseBean<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1327394867498600287L;
}
