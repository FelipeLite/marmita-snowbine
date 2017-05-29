package br.com.snowbine.sistema.model;

import javax.faces.bean.ManagedBean;

import br.com.snowbine.base.modal.ViewModals;
import br.com.snowbine.sistema.bean.ClienteBean;

@ManagedBean(name = "clientModal")
public class ClienteModel extends ViewModals
{
	// Define qual o bean do pai
	public void openDialog(String formSelecionada, Integer idEntidade)
	{
		ClienteBean clienteBean = new ClienteBean();
		super.baseBean = clienteBean;
		super.openDialog(formSelecionada, idEntidade);
	}
}
