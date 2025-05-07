package enviarCorreo;

import java.io.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreo {

public String receptor = "cesar@gmail.com"; // MODIFICAR PARA PONER LA DIRECCION DE CORREO DEL RECEPTOR
		 
public EnviarCorreo (String Receptor, String Mensaje){
		
	receptor= Receptor;
	try{
	        
		final String usuario = "emailpruebais1.2025@gmail.com"; // debe ser válido
        final String a = "rmnq oqvx gqub fhdb";//inseguro, pero bueno, es solo un correo para las pruebas
       
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");  // Si activamos, entonces hay que autenticarse
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			
			Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(usuario, a);
	            }
	        });
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(usuario));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receptor));
				message.setSubject("Solicitud Aceptada");
				message.setText(Mensaje);
	 
				Transport.send(message);
	 
				System.out.println("Hecho");
	 
			} catch (MessagingException e) {
				
				throw new RuntimeException(e);
			}
		}
	catch (Exception e) {System.out.println("Error: "+e.getMessage());}
	}
}
