package br.com.snowbine.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

public class UploadUtils
{

	public static String uploadImage(Part imagemPerfil)
	{
		System.out.println("upload");
		
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		
		try
		{
			//Gera um nome de arquivo unico
			String nomeArquivo = "C:\\Users\\f_nev\\git\\marmita-snowbine\\src\\main\\assets\\images\\perfil-usuario\\" + generateRandomName()+".jpg";
			
			inputStream = imagemPerfil.getInputStream();
			outputStream = new FileOutputStream(nomeArquivo);

			byte[] buffer = new byte[4096];
			int bytesRead = 0;
			
			while(true)
			{
				bytesRead = inputStream.read(buffer);
				
				if (bytesRead > 0)
				{
					outputStream.write(buffer, 0, bytesRead);
					
				} 
				
				else
				{
					break;
				}
			}
			
			//Redimensiona a imagem para ficar padrão de perfil
			redimensionarImagem30x30(nomeArquivo);
			
			return nomeArquivo;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		finally
		{
			try
			{
				outputStream.close();
				inputStream.close();
			} 
			
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	
	private static String generateRandomName()
	{
		UUID generatedName = UUID.randomUUID();
		
		return generatedName.toString();
	}
	
	public static void redimensionarImagem30x30(String fileName)
	{
		try
		{
			System.out.println("Redimensionando a imagem");
			BufferedImage imagemOriginal = ImageIO.read(new File(fileName));
			
			int type = imagemOriginal.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : imagemOriginal.getType();
			
			BufferedImage imagem30x30jpg = ResizeImage.resizeImage(imagemOriginal, type, 30, 30);
			ImageIO.write(imagem30x30jpg, "jpg", new File(fileName));
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
