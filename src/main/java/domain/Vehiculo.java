package domain;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Vehiculo implements Serializable {
	private byte[] foto;
	@XmlID
	@Id 
	@GeneratedValue
	private Integer vehiculoNumber;
	private String marca;
	private String modelo;
	private int plazas;
	private Driver driver;
	
	public Vehiculo(File pFoto,String pMarca, String pModelo, int pPlazas, Driver pDriver) {
		setFoto(pFoto);
		marca=pMarca;
		modelo=pModelo;
		plazas=pPlazas;
		driver=pDriver;
		
	}
	
	public BufferedImage getFoto() {
		try {
			return ImageIO.read(new ByteArrayInputStream(foto));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	public void setFoto(File foto) {
		try {
			this.foto = this.imgToBytes(foto, "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getVehiculoNumber() {
		return vehiculoNumber;
	}

	public void setVehiculoNumber(Integer vehiculoNumber) {
		this.vehiculoNumber = vehiculoNumber;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	private byte[] imgToBytes(File image, String format) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write((RenderedImage) ImageIO.read(image), format, baos); // format puede ser "jpg", "png", etc.
	    return baos.toByteArray();
	}
	
	public String toString() {
		return marca+" "+modelo+" "+vehiculoNumber;
	}
}
