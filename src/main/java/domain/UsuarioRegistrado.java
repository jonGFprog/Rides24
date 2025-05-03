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
	private Double saldo;
	
	public UsuarioRegistrado(String pEmail,String pPassword) {	
		email=pEmail;
		password=pPassword;
		saldo=0.00;
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
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double s) {
		saldo= s;
	}
	
}
