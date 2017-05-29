package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.sistema.dao.EstadoDao;
import br.com.snowbine.sistema.entity.Estado;

@ManagedBean
@SessionScoped
public class EstadoBean extends BaseBean<Estado, EstadoDao> implements Serializable
{
	private static final long serialVersionUID = -8048764765446286370L;
	
}
