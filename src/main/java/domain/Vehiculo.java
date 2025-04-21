package domain;

import java.io.File;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Vehiculo {
	private File foto;
	private String marca;
	private String modelo;
	private int plazas;
	
	public Vehiculo(File pFoto,String pMarca, String pModelo, int pPlazas) {
		foto=pFoto;
		marca=pMarca;
		modelo=pModelo;
		plazas=pPlazas;
	}
	
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	
	
}
