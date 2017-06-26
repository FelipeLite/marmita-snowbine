package br.com.snowbine.sistema.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthentication extends Authenticator
{
	String usuario;
	String senha;
	
	public EmailAuthentication(String usuario, String senha)
	{
		super();
		
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(usuario, senha);
	}
}
