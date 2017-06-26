
package br.com.snowbine.sistema.util;

import java.util.Random;

public abstract class LoginUtils
{
	public static String gerarNovaSenha()
	{
		//Caracteres possiveis de senhas
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		
		//Gera uma senha de oito caracteres
		for(int i = 0; i < chars.length - 27; i++)
		{
			char c = chars[random.nextInt(chars.length)];
			builder.append(c);
		}
		
		return builder.toString();
	}
}
