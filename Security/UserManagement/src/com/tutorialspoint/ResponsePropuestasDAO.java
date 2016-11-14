package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ResponsePropuestasDAO {

	private boolean errorEnCampo=false;
	private ValidadorCampos validadorCampos=new ValidadorCampos();
	List<Propuesta> resultList = new ArrayList<Propuesta>();
	private Connection connect=null;
	private PreparedStatement preparedStatement=null;

	public List<Propuesta> obtenerPropuestas(String usuario, String iDsPropuestas) {

		errorEnCampo = validadorCampos.validarUsuario(usuario);
		if (errorEnCampo) {
			resultList.add(new Propuesta("EC000", "Error en campo de entrada usuario"));
			return resultList;
		}

		ResultSet resultado = null;
		if (iDsPropuestas.equals("ALL")) {

			try {

				abrirConexion(usuario);
				resultado = consultarPropuestas();
				propuesta2XML(resultado);
				if (resultado != null)
					resultado.close();

			} catch (ClassNotFoundException | SQLException e) {

				List<Propuesta> resultError = new ArrayList<Propuesta>();
				resultError.add(new Propuesta("EDB001", "Problemas con la base de datos"));
				return resultError;

			} finally {
				cerrarConexion();
			}

		} else {
			StringTokenizer propuestas = new StringTokenizer(iDsPropuestas, ";");
			String propuesta = "";

			try {
				abrirConexion(usuario);

				while (propuestas.hasMoreElements()) {
					propuesta = propuestas.nextElement().toString();
					System.out.println(propuesta);

					errorEnCampo = validadorCampos.validarPropuestaID(propuesta);
					if (errorEnCampo) {
						List<Propuesta> resultError = new ArrayList<Propuesta>();
						resultError.add(new Propuesta("EC011", "Error en campo de entrada propuestaID"));
						return resultError;
					}

					resultado = consultarPropuesta(propuesta);
					propuesta2XML(resultado);
					if (resultado != null)
						resultado.close();

				}

			} catch (ClassNotFoundException | SQLException e) {

				List<Propuesta> resultError = new ArrayList<Propuesta>();
				resultError.add(new Propuesta("EDB001", "Problemas con la base de datos"));
				return resultError;
			} finally {
				cerrarConexion();
			}

		}

		return resultList;
	}

	private void cerrarConexion() {

		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void abrirConexion(String usuario) throws ClassNotFoundException, SQLException {
		String password = usuario;
		String stringConnection = "jdbc:mysql://localhost/service_desk?useSSL=true" + "&user=" + usuario + "&password="
				+ password;

		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(stringConnection);

	}

	private void propuesta2XML(ResultSet resultado) throws SQLException {
		
		Propuesta propuesta;
        while (resultado.next()) {
        	
        	propuesta= new Propuesta( 
        			resultado.getString("propuesta_cliente_id"), 
        			resultado.getString("propuesta_socio_id"), 
        			resultado.getString("cliende_id"), 
        			resultado.getString("fecha_presentacion_propuesta"), 
        			resultado.getString("estado_propuesta"), 
        			resultado.getString("proyecto_id"), 
        			resultado.getString("viable_tecnicamente"), 
        			resultado.getString("viable_financieramente"), 
        			resultado.getString("fecha_aceptacion"), 
        			resultado.getString("fecha_creacion_propuesta"), 
        			resultado.getString("valor_total"));
        	propuesta.setStatus("OK");
        	resultList.add(propuesta);

    }

	}

	private ResultSet consultarPropuesta(String propuesta) throws SQLException {

		String select = "SELECT " 
				+ "propuesta_cliente_id,"
				+ "cliende_id," 
				+ "propuesta_socio_id," 
				+ "fecha_creacion_propuesta,"
				+ "fecha_presentacion_propuesta,"
				+ "estado_propuesta," 
				+ "proyecto_id," 
				+ "viable_tecnicamente,"
				+ "viable_financieramente," 
				+ "fecha_aceptacion," 
				+ "valor_total, " 
				+ "FROM PROPUESTA_CLIENTE"
				+ "WHERE propuesta_cliente_id=?";

		// Statements allow to issue SQL queries to the database
		preparedStatement = connect.prepareStatement(select);
		preparedStatement.setString(1, propuesta);
		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet;
	}
	
	private ResultSet consultarPropuestas() throws SQLException {

		String select = "SELECT " 
				+ "propuesta_cliente_id,"
				+ "cliende_id," 
				+ "propuesta_socio_id," 
				+ "fecha_creacion_propuesta,"
				+ "fecha_presentacion_propuesta,"
				+ "estado_propuesta," 
				+ "proyecto_id," 
				+ "viable_tecnicamente,"
				+ "viable_financieramente," 
				+ "fecha_aceptacion," 
				+ "valor_total, " 
				+ "FROM PROPUESTA_CLIENTE";

		// Statements allow to issue SQL queries to the database
		preparedStatement = connect.prepareStatement(select);
		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet;
	}

}
