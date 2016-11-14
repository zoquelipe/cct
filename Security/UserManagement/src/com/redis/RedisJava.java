package com.redis;
public class RedisJava {
	 public static void main(String[] args) 
	 {
	      long startTime = System.currentTimeMillis();
	      
	      Proyecto proyecto = new Proyecto();
	      
	      for(int i=0;i<1000;i++){
	    	  proyecto.setId(""+i);
	    	  proyecto.setNombreProyecto("Help");
		      proyecto.setMovimiento(tipoMovimiento.ingreso);
		      proyecto.setValor("5000"+i);
		      proyecto.setFecha("14102016");
		      proyecto.insertarProyecto(proyecto);
	      }
	      Proyecto cargarProy = new Proyecto();
	      for(int i=0;i<1000;i++){
	    	  
	    	  cargarProy = Proyecto.cargarProyecto(""+i);
		      System.out.println("Nombre proyecto " + cargarProy.getNombreProyecto());
		      System.out.println("Tipo movimiento " + cargarProy.getMovimiento());
		      System.out.println("Valor " + cargarProy.getValor());
		      System.out.println("Fecha " + cargarProy.getFecha());
	      }
	      
	      long stopTime = System.currentTimeMillis();
	      System.out.println(stopTime - startTime);
	       
	  }
}

