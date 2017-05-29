package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.sistema.dao.CidadeDao;
import br.com.snowbine.sistema.entity.Cidade;

@ManagedBean
@SessionScoped
public class CidadeBean extends BaseBean<Cidade, CidadeDao> implements Serializable
{
	private static final long serialVersionUID = 6854638251996187578L;
	
}
