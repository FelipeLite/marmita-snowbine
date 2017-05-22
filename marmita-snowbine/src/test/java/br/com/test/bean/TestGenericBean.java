package br.com.test.bean;

import java.util.List;

import br.com.snowbine.bean.EstadoBean;
import br.com.snowbine.entity.Estado;

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
