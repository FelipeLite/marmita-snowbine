package br.com.test.sistema;

import br.com.snowbine.sistema.util.LoginUtils;

public class TestGerarSenha
{
	public static void main(String[] args)
	{
		System.out.println(LoginUtils.gerarNovaSenha());
	}
}
