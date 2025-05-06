package enviarCorreo;

import java.io.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreo {

public String receptor = "cesar@gmail.com"; // MODIFICAR PARA PONER LA DIRECCI�N DE CORREO DEL RECEPTOR
		 
public EnviarCorreo (String Receptor, String Mensaje){
		
	receptor= Receptor;
	try{
	            
			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");  // Si activamos, entonces hay que autenticarse
//			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.ehu.es");
//			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props);
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("cesar@cesar.com"));
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
