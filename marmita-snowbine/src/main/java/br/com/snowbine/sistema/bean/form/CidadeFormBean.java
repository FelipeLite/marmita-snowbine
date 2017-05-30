package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.CidadeDao;
import br.com.snowbine.sistema.entity.Cidade;

@ManagedBean
@SessionScoped
public class CidadeFormBean extends BaseBeanForm<Cidade, CidadeDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
}
