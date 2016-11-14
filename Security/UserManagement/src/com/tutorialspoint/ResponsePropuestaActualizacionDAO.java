package com.tutorialspoint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsePropuestaActualizacionDAO {

	public List<Response> actualizarPropuesta(String usuario, String propuestaID, String propuestaSocioID, String clienteID,
			String fechaPropuesta, String estado, String proyectoID, String viabilidadTecnica,
			String viabilidadEconomica, String fechaAceptacion, String fechaCreacion, String valorTotal) {

		ValidadorCampos validadorCampos = new ValidadorCampos();
		ValidadorUsuarios validadorUsuarios = new ValidadorUsuarios();
		Boolean errorEnCampo = false;
		List<Response> resultList = new ArrayList<Response>();
		AccesoDB actualizador = new AccesoDB();

		errorEnCampo = validadorCampos.validarUsuario(usuario);
		if (errorEnCampo)
			resultList.add(new Response("EC000", "Error en campo de entrada usuario"));

		errorEnCampo = validadorCampos.validarPropuestaSocioID(propuestaSocioID);
		if (errorEnCampo)
			resultList.add(new Response("EC001", "Error en campo de entrada propuestaSocioID"));

		errorEnCampo = validadorCampos.validarClienteID(clienteID);
		if (errorEnCampo)
			resultList.add(new Response("EC002", "Error en campo de entrada clienteID"));

		errorEnCampo = validadorCampos.validarFechaPropuesta(fechaPropuesta);
		if (errorEnCampo)
			resultList.add(new Response("EC003", "Error en campo de entrada fechaPropuesta"));

		errorEnCampo = validadorCampos.validarEstad(estado);
		if (errorEnCampo)
			resultList.add(new Response("EC004", "Error en campo de entrada estado"));

		errorEnCampo = validadorCampos.validarProyectoID(proyectoID);
		if (errorEnCampo)
			resultList.add(new Response("EC005", "Error en campo de entrada proyectoID"));

		errorEnCampo = validadorCampos.validarViabilidadTecnica(viabilidadTecnica);
		if (errorEnCampo)
			resultList.add(new Response("EC006", "Error en campo de entrada viabilidadTecnica"));

		errorEnCampo = validadorCampos.validarViabilidadEconomica(viabilidadEconomica);
		if (errorEnCampo)
			resultList.add(new Response("EC007", "Error en campo de entrada viabilidadEconomica"));

		errorEnCampo = validadorCampos.validarFechaAceptacion(fechaAceptacion);
		if (errorEnCampo)
			resultList.add(new Response("EC008", "Error en campo de entrada fechaAceptacion"));

		errorEnCampo = validadorCampos.validarFechaCreacion(fechaCreacion);
		if (errorEnCampo)
			resultList.add(new Response("EC009", "Error en campo de entrada fechaCreacion"));

		errorEnCampo = validadorCampos.validarValorTotal(valorTotal);
		if (errorEnCampo)
			resultList.add(new Response("EC010", "Error en campo de entrada valorTotal"));

		errorEnCampo = validadorCampos.validarPropuestaID(propuestaID);
		if (errorEnCampo)
			resultList.add(new Response("EC011", "Error en campo de entrada propuestaID"));
		
		if (!resultList.isEmpty())
			return resultList;

		if (!validadorUsuarios.usuarioValido(usuario, propuestaID)) {

			resultList.add(new Response("EA001", "Usuario no valido para la transaccion"));
			return resultList;

		}

		try {
			
			actualizador.actualizar(
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
			
		} catch (ClassNotFoundException | SQLException e) {
			resultList.add(new Response("EDB001", "Problemas con la base de datos"));
			return resultList;
		}
			
		resultList.add(new Response("OK", "Success"));
		return resultList;
	}

}
