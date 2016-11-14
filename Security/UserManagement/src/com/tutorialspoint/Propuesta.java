package com.tutorialspoint;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "propuesta")
public class Propuesta implements Serializable {

	public Propuesta() {
	}

	public Propuesta(String propuestaID, String propuestaSocioID, String clienteID,
			String fechaPropuesta, String estado, String proyectoID, String viabilidadTecnica,
			String viabilidadEconomica, String fechaAceptacion, String fechaCreacion, String valorTotal) {

		this.propuestaID = propuestaID;
		this.propuestaSocioID = propuestaSocioID;
		this.clienteID = clienteID;
		this.fechaPropuesta = fechaPropuesta;
		this.estado = estado;
		this.proyectoID = proyectoID;
		this.viabilidadTecnica = viabilidadTecnica;
		this.viabilidadEconomica = viabilidadEconomica;
		this.fechaAceptacion = fechaAceptacion;
		this.fechaCreacion = fechaCreacion;
		this.valorTotal = valorTotal;
	}

	private static final long serialVersionUID = 1L;

	private String propuestaID = "";
	private String propuestaSocioID = "";
	private String clienteID = "";
	private String fechaPropuesta = "";
	private String estado = "";
	private String proyectoID = "";
	private String viabilidadTecnica = "";
	private String viabilidadEconomica = "";
	private String fechaAceptacion = "";
	private String fechaCreacion = "";
	private String valorTotal = "";

	private String status = "";
	private String descripcion = "";

	public Propuesta(String status, String descripcion) {
		this.status = status;
		this.descripcion = descripcion;
	}

	public String getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@XmlElement
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPropuestaID() {
		return propuestaID;
	}

	@XmlElement
	public void setPropuestaID(String propuestaID) {
		this.propuestaID = propuestaID;
	}

	public String getPropuestaSocioID() {
		return propuestaSocioID;
	}

	@XmlElement
	public void setPropuestaSocioID(String propuestaSocioID) {
		this.propuestaSocioID = propuestaSocioID;
	}

	public String getClienteID() {
		return clienteID;
	}

	@XmlElement
	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}

	public String getFechaPropuesta() {
		return fechaPropuesta;
	}

	@XmlElement
	public void setFechaPropuesta(String fechaPropuesta) {
		this.fechaPropuesta = fechaPropuesta;
	}

	public String getEstado() {
		return estado;
	}

	@XmlElement
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getProyectoID() {
		return proyectoID;
	}

	@XmlElement
	public void setProyectoID(String proyectoID) {
		this.proyectoID = proyectoID;
	}

	public String getViabilidadTecnica() {
		return viabilidadTecnica;
	}

	@XmlElement
	public void setViabilidadTecnica(String viabilidadTecnica) {
		this.viabilidadTecnica = viabilidadTecnica;
	}

	public String getViabilidadEconomica() {
		return viabilidadEconomica;
	}

	@XmlElement
	public void setViabilidadEconomica(String viabilidadEconomica) {
		this.viabilidadEconomica = viabilidadEconomica;
	}

	public String getFechaAceptacion() {
		return fechaAceptacion;
	}

	@XmlElement
	public void setFechaAceptacion(String fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	@XmlElement
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	@XmlElement
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

}
