package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

		try {
			if(!integridadInformacion()){		
				if (errorEnCampo) {
					resultList.add(new Propuesta("EI001", "Existe un problema con la integridade de la informacion"));
					System.out.println("Existe un problema con la integridade de la informacion");
					return resultList;	
				}
			}
		} catch (SQLException e1) {
			List<Propuesta> resultError = new ArrayList<Propuesta>();
			resultError.add(new Propuesta("EDB001", "Problemas con la base de datos [1]"));
			return resultError;
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
				resultError.add(new Propuesta("EDB001", "Problemas con la base de datos [2]"));
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
				resultError.add(new Propuesta("EDB001", "Problemas con la base de datos [3]"));
				return resultError;
			} finally {
				cerrarConexion();
			}

		}

		return resultList;
	}

	private boolean integridadInformacion() throws SQLException {

        ResultSet resultSetCopia = null;
        ResultSet resultSetOriginal=null;
        
        //Extrayendo datos de la tabla original
        try {
            resultSetOriginal = conexionPropOriginal();

        } catch (Exception e) {
            System.out.println("Hubo un error tabla original: " + e.getMessage());
        }

        //Extrayendo datos de la tabla copia
        try {

            resultSetCopia = conexionPropCopia();
        } catch (Exception e) {
            System.out.println("Hubo un error tabla copia: " + e.getMessage());
        }

        //A partir de aquí comienza la comparación
        boolean iguales = true;

        while (resultSetOriginal.next()) {
            resultSetCopia.next();
            ResultSetMetaData resultSetMetaData = resultSetOriginal.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (!resultSetOriginal.getObject(i).equals(resultSetCopia.getObject(i))) {
                    iguales=false;
                }
            }
        }

		return iguales;
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
		String password = "admin";
		String stringConnection = "jdbc:mysql://localhost/cct_db?useSSL=false" + "&user=" + usuario + "&password="
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
	
	Connection conPropuestaOriginal=null;
	Connection conPropuestaCopia = null;
	
    public ResultSet conexionPropOriginal() {
    	ResultSet resultSetOriginal=null;
    	try {
            Class.forName("com.mysql.jdbc.Driver");
             conPropuestaOriginal = DriverManager.getConnection("jdbc:mysql://localhost/cct_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&user=root&password=admin");
             String campos="propuesta_cliente_id, cliente_id, propuesta_socio_id, fecha_creacion_propuesta, fecha_presentacion_propuesta, estado_propuesta, proyecto_id, viable_tecnicamente, viable_financieramente, fecha_aceptacion, valor_total";

             PreparedStatement preparedStatementOriginal = conPropuestaOriginal.prepareStatement("SELECT " + campos + " FROM propuesta_cliente");
             resultSetOriginal = preparedStatementOriginal.executeQuery();
             
             conPropuestaOriginal.close();
             
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return resultSetOriginal;
    }
    
    public ResultSet conexionPropCopia() {
    	ResultSet resultSetCopia=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conPropuestaCopia = DriverManager.getConnection("jdbc:mysql://localhost/cct_db_segura?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&user=root&password=admin");

            String campos="propuesta_cliente_id, cliente_id, propuesta_socio_id, fecha_creacion_propuesta, fecha_presentacion_propuesta, estado_propuesta, proyecto_id, viable_tecnicamente, viable_financieramente, fecha_aceptacion, valor_total";

            PreparedStatement preparedStatementOriginal = conPropuestaOriginal.prepareStatement("SELECT " + campos + " FROM propuesta_cliente");
            resultSetCopia = preparedStatementOriginal.executeQuery();
            
            conPropuestaCopia.close();
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return resultSetCopia;
    }
	

}
