package br.com.snowbine.sistema.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class EmailUtils
{
	//Email configurations
	private static final String emailFrom = "felipe.alneves@outlook.com";
	private static Properties properties = System.getProperties();
	private static final String subject = "Recuperação de senha Marmita Snowbine";
	private static final String password = "Zubrurack3d";
	private static final String smtpHost = "smtp-mail.outlook.com";
	private static final String smtpPort = "587";
	
	
	public static void sendEmail(String destinatario, String usuario, String senha)
	{
		//Seta properties
		properties.setProperty("mail.smtp.auth", "true"); 
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", smtpHost);
		properties.setProperty("mail.smtp.user", emailFrom);   
		properties.setProperty("mail.smtp.password", password); 
	    properties.setProperty("mail.smtp.port", smtpPort);
	
		//Pega dados da sessao
		Session session = Session.getDefaultInstance(properties, new EmailAuthentication(emailFrom, password));
		
		//Mensagem que será enviada
		String mensagem = "<h2> Olá " + usuario + "</h2>"
				+ "<p> Você solicitou a recuperação de senha.<p>Sua nova senha é: <strong><h2>" + senha +" </h2></strong></p>";
		
		try
		{
			//Cria um mimeMessage padrão
			MimeMessage message = new MimeMessage(session);	
			
			//Seta o remetente
			message.setFrom(new InternetAddress(emailFrom));
			
			//Seta o destinatário
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			
			//Assunto
			message.setSubject(subject);
			
			//Define a mensagem em html
			message.setContent(mensagem, "text/html;charset=UTF-8");
			
			//Envia a mensagem
			Transport.send(message);
			System.out.println("Mensagem de recuperação de senha enviada para: " + destinatario);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
