package br.com.snowbine.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class StringUtils
{
	public static String criptografarSenha(String senha)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, digest.digest(senha.getBytes()));
			
			return String.format("%32x", hash);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
