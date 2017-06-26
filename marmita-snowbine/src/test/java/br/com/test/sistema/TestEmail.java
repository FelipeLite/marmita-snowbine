package br.com.test.sistema;

import br.com.snowbine.sistema.util.EmailUtils;

public class TestEmail
{
	public static void main(String[] args)
	{
		String destinatario = "felipe.alneves@outlook.com";
		String senha = "teste";
		
		EmailUtils.sendEmail(destinatario, "Felipe", senha);
	}
}
