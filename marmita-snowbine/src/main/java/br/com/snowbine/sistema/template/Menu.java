package br.com.snowbine.sistema.template;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("menu")
@ViewScoped
public class Menu
{
	@PostConstruct
	public void init()
	{
		System.out.println("iniciando");
	}
	public void teste()
	{
		System.out.println("dentro do teste");
	}
}
