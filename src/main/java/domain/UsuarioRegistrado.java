package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
@Entity
public class UsuarioRegistrado {
	@XmlID
	@Id 
	private String email;
	private String password;
	
	
	public UsuarioRegistrado(String pEmail,String pPassword) {	
		email=pEmail;
		password=pPassword;
		
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pPassword) {
		password=pPassword;
	}
	
	
	
}
