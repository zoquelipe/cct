package com.tutorialspoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCampos {

	public Boolean validarPropuestaSocioID(String propuestaSocioID) {

		if (propuestaSocioID.length() != 10)
			return true;
		if (tieneCaracteresEspeciales(propuestaSocioID))
			return true;

		return false;
	}

	public Boolean validarClienteID(String clienteID) {

		if (clienteID.length() != 10)
			return true;
		if (tieneCaracteresEspeciales(clienteID))
			return true;

		return false;
	}

	public Boolean validarFechaPropuesta(String fechaPropuesta) {
		if (fechaPropuesta.length() != 6)
			return true;
		if (tieneCaracteresEspeciales(fechaPropuesta))
			return true;

		return false;
	}

	public Boolean validarEstad(String estado) {
		if (estado.length() > 10)
			return true;
		if (tieneCaracteresEspeciales(estado))
			return true;

		return false;
	}

	public Boolean validarProyectoID(String proyectoID) {
		if (proyectoID.length() != 10)
			return true;
		if (tieneCaracteresEspeciales(proyectoID))
			return true;

		return false;
	}

	public Boolean validarViabilidadTecnica(String viabilidadTecnica) {
		if (viabilidadTecnica.length() > 10)
			return true;
		if (tieneCaracteresEspeciales(viabilidadTecnica))
			return true;

		return false;
	}

	public Boolean validarViabilidadEconomica(String viabilidadEconomica) {
		if (viabilidadEconomica.length() > 10)
			return true;
		if (tieneCaracteresEspeciales(viabilidadEconomica))
			return true;

		return false;
	}

	public Boolean validarFechaAceptacion(String fechaAceptacion) {
		if (fechaAceptacion.length() != 6)
			return true;
		if (tieneCaracteresEspeciales(fechaAceptacion))
			return true;

		return false;
	}

	public Boolean validarFechaCreacion(String fechaCreacion) {
		if (fechaCreacion.length() != 6)
			return true;
		if (tieneCaracteresEspeciales(fechaCreacion))
			return true;

		return false;
	}

	public Boolean validarValorTotal(String valorTotal) {
		if (valorTotal.length() > 15)
			return true;
		if (tieneCaracteresEspeciales(valorTotal))
			return true;

		return false;
	}

	public boolean tieneCaracteresEspeciales(String string) {

		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(string);
		boolean b = m.find();
		return b;

	}

	public Boolean validarUsuario(String usuario) {
		if (usuario.length() != 10)
			return true;
		if (tieneCaracteresEspeciales(usuario))
			return true;

		return false;
	}

	public Boolean validarPropuestaID(String propuestaID) {

		if (propuestaID.length() != 10)
			return true;
		if (tieneCaracteresEspeciales(propuestaID))
			return true;

		return false;
	}

}
