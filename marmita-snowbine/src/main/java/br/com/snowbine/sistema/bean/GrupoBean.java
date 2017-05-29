package br.com.snowbine.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.sistema.dao.GrupoDao;
import br.com.snowbine.sistema.entity.Grupo;

@ManagedBean
@SessionScoped
public class GrupoBean extends BaseBean<Grupo, GrupoDao> implements Serializable
{
	private static final long serialVersionUID = 8526391194338493939L;
}
