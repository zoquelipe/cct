package com.tutorialspoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.lmax.inputdisruptor.InputDisruptor;
import com.lmax.outputdisruptor.OutputDisruptor;

import edu.uniandes.cct.serviceRequest.ServiceDeskManager;

@Path("/UserService")
public class UserService {
	private final static Logger logger = Logger.getLogger(UserService.class);

	UserDao userDao = new UserDao();
	ResponsePropuestaActualizacionDAO actualizarPropuesta = new ResponsePropuestaActualizacionDAO();
	ResponsePropuestasDAO propuesta = new ResponsePropuestasDAO();
	MonitoringInfoDao MonitoringInfoDao = new MonitoringInfoDao();
	private static ServiceDeskManager sdm;

	public UserService() {
	/*	System.gc();
		try {
			InputDisruptor.startProcessing();
			OutputDisruptor.startProcessing();
			if (sdm == null) {
				sdm = new ServiceDeskManager();
				sdm.StartAsyncProcessing();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Esto es información:" + "UserService class failed, please contact system admin!");
		}
		*/
	}

	// @GET
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public List<User> getUsers(@FormParam("ID") String id, @FormParam("Nombre") String nombre,
			@FormParam("Descripcion") String descripcion, @FormParam("Cargo") String cargo,
			@FormParam("Area") String area) {
		System.out.println(id);
		System.out.println(nombre);
		System.out.println(descripcion);
		return userDao.getAllUsers(Integer.parseInt(id), nombre, descripcion, cargo, area);
	}

	// @GET
	@POST
	@Path("/users2")
	// @Produces(MediaType.APPLICATION_XML)
	// @Consumes("application/x-www-form-urlencoded")
	public boolean getUsers2(@FormParam("IDS") String idList) {
		// System.out.println(idList);
		return userDao.getAllUsers2(idList);
	}

	@GET
	@Path("/monitoring")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public MonitoringInfo monitoring() {
		return MonitoringInfoDao.getStatusInfo();
	}

	// Inicio_Cambio_Sprint_Seguridad
	@POST
	@Path("/actualizarPropuesta")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public List<Response> actualizarPropuesta(
			@FormParam("usuario") String usuario,
			@FormParam("propuestaID") String propuestaID,
			@FormParam("propuestaSocioID") String propuestaSocioID,
			@FormParam("clienteID") String clienteID,
			@FormParam("fechaPropuesta") String fechaPropuesta,
			@FormParam("estado") String estado, 
			@FormParam("proyectoID") String proyectoID,
			@FormParam("viabilidadTecnica") String viabilidadTecnica,
			@FormParam("viabilidadEconomica") String viabilidadEconomica,
			@FormParam("fechaAceptacion") String fechaAceptacion, 
			@FormParam("fechaCreacion") String fechaCreacion,
			@FormParam("valorTotal") String valorTotal) {
		
		return actualizarPropuesta.actualizarPropuesta(
				usuario,
				propuestaID,
				propuestaSocioID, 
				clienteID, 
				fechaPropuesta, 
				estado, 
				proyectoID,
				viabilidadTecnica, 
				viabilidadEconomica, 
				fechaAceptacion,
				fechaCreacion,
				valorTotal);

	}
	
	@POST
	@Path("/consutarPropuesta")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public List<Propuesta> consultarPropuesta(
			@FormParam("usuario") String usuario,
			@FormParam("IDsPropuestas") String IDsPropuestas) {
		
		return propuesta.obtenerPropuestas(usuario,IDsPropuestas);
		
	}
	
}