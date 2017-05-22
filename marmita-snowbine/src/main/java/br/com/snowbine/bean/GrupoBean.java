package br.com.snowbine.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.snowbine.base.bean.BaseBean;
import br.com.snowbine.dao.GrupoDao;
import br.com.snowbine.entity.Grupo;

@ManagedBean
@SessionScoped
public class GrupoBean extends BaseBean<Grupo, GrupoDao> implements Serializable
{
	private static final long serialVersionUID = 8526391194338493939L;
}
