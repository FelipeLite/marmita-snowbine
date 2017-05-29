package br.com.test.bean;

import java.util.List;

import br.com.snowbine.sistema.bean.EstadoBean;
import br.com.snowbine.sistema.entity.Estado;

public class TestGenericBean
{
	public static void main(String[] args)
	{
		EstadoBean bean = new EstadoBean();
		
		List<Estado> estados = bean.listar();
		
		for(Estado e : estados)
		{
			System.out.println(e.getNome());
		}
	}
}
