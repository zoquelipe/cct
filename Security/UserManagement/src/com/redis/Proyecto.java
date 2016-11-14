package com.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.*;
import redis.clients.*;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.*;

public class Proyecto 
{
	private String id;
	private String nombreProyecto;
	private tipoMovimiento movimiento;
	private String valor;
	private String fecha;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * @return the nombreProyecto
	 */
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	/**
	 * @param nombreProyecto the nombreProyecto to set
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 * @return the movimiento
	 */
	public tipoMovimiento getMovimiento() {
		return movimiento;
	}

	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(tipoMovimiento movimiento) {
		this.movimiento = movimiento;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


public void insertarProyecto(Proyecto proyecto)
{
	Jedis jedis = new Jedis("localhost");
	Map<String, String> propProyecto = new HashMap<String, String>();
	propProyecto.put("nombreProyecto", proyecto.getNombreProyecto());
	propProyecto.put("movimiento", proyecto.getMovimiento().toString());
	propProyecto.put("valor", proyecto.getValor());
	propProyecto.put("fecha", proyecto.getFecha());
	jedis.hmset("proyecto:" + proyecto.getId(), propProyecto);
	jedis.close();
}

public static Proyecto cargarProyecto(String idProyecto){
	Jedis jedis = new Jedis("localhost");
	Proyecto proyecto = new Proyecto();
	Map<String, String> properties = jedis.hgetAll("proyecto:" + idProyecto);
    proyecto.setNombreProyecto(properties.get("nombreProyecto"));
    proyecto.setMovimiento(tipoMovimiento.valueOf(properties.get("movimiento")));
    proyecto.setValor(properties.get("valor"));
    proyecto.setFecha(properties.get("fecha"));
	jedis.close();
    return proyecto;
}

}