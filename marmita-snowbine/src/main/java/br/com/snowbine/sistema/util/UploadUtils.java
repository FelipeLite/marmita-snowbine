package br.com.snowbine.sistema.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class UploadUtils
{

	public static String uploadImage(Part imagemPerfil)
	{
		InputStream inputStream = null;
		FileOutputStream outputStream = null;

		try
		{
			// Gera um nome de arquivo unico
			String nomeArquivo = "C:\\Users\\f_nev\\git\\marmita-snowbine\\src\\main\\assets\\images\\perfil-usuario\\"
					+ generateRandomName() + ".jpg";

			inputStream = imagemPerfil.getInputStream();
			outputStream = new FileOutputStream(nomeArquivo);

			byte[] buffer = new byte[4096];
			int bytesRead = 0;

			while (true)
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

			// Redimensiona a imagem para ficar padrão de perfil
			redimensionarImagem40x40(nomeArquivo);

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

	public static void redimensionarImagem40x40(String fileName)
	{
		try
		{
			BufferedImage imagemOriginal = ImageIO.read(new File(fileName));

			int type = imagemOriginal.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : imagemOriginal.getType();

			BufferedImage imagem40x40jpg = ResizeImage.resizeImage(imagemOriginal, type, 40, 40);
			ImageIO.write(imagem40x40jpg, "jpg", new File(fileName));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String upload(FileUploadEvent event)
	{
		UploadedFile uploadedFile = event.getFile();
		
		//Serve para saber o nome do arquivo gerado e não salvar o caminho completo no banco
		String generatedName = generateRandomName() + uploadedFile.getFileName();
		
		String nomeArquivo = "C:\\Users\\f_nev\\git\\marmita-snowbine\\src\\main\\webapp\\resources\\images\\profile\\" + generatedName;
		try
		{
			File file = new File(nomeArquivo);

			OutputStream out = new FileOutputStream(file);
			out.write(uploadedFile.getContents());
			out.close();

			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Upload completo", "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
		} 
		
		catch (IOException e)
		{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
		
		return "/images/profile/" + generatedName;

	}
}
