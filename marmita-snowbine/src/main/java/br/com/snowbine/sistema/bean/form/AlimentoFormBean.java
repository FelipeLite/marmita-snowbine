package br.com.snowbine.sistema.bean.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.snowbine.base.bean.form.BaseBeanForm;
import br.com.snowbine.sistema.dao.AlimentoDao;
import br.com.snowbine.sistema.entity.Alimento;

@Named
@ViewScoped
public class AlimentoFormBean extends BaseBeanForm<Alimento, AlimentoDao> implements Serializable
{
	private static final long serialVersionUID = 1L;
}
