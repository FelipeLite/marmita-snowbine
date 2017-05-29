package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.sistema.dao.EnderecoDao;
import br.com.snowbine.sistema.entity.Endereco;

@ManagedBean
@SessionScoped
public class EnderecoBean extends BaseBean<Endereco, EnderecoDao> implements Serializable
{
	private static final long serialVersionUID = -4812922714060518329L;

}
