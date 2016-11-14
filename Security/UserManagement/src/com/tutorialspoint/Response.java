package com.tutorialspoint;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private String estado;
	private String descripcion;

	public Response(){
		
	}
	
	public Response(String estado, String descripcion) {

		this.estado = estado;
		this.descripcion = descripcion;

	}

	public String getEstado() {
		return estado;
	}

	@XmlElement
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@XmlElement
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
