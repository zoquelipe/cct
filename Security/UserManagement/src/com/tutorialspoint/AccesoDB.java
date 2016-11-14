package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoDB {

	private Connection connect;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public void actualizar(String usuario, String propuestaID, String propuestaSocioID, String clienteID,
			String fechaPropuesta, String estado, String proyectoID, String viabilidadTecnica,
			String viabilidadEconomica, String fechaAceptacion, String fechaCreacion, String valorTotal)
			throws ClassNotFoundException, SQLException {

		// This will load the MySQL driver, each DB has its own driver

		// Setup the connection with the DB
		String password = usuario;
		String stringConnection = "jdbc:mysql://localhost/service_desk?useSSL=true"
		        + "&user=" + usuario
				+ "&password=" + password;
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(stringConnection);

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		// PreparedStatements can use variables and are more efficient
		String update = "UPDATE PROPUESTA_CLIENTE SET "
				+ "propuesta_socio_id=?,"
				+ "fecha_presentacion_propuesta=?,"
				+ "estado_propuesta=?,"
				+ "proyecto_id=?,"
				+ "viable_tecnicamente=?,"
				+ "viable_financieramente=?,"
				+ "fecha_aceptacion=?,"
				+ "valor_total=? "
				+ "WHERE propuesta_cliente_id=?";
		
		preparedStatement = connect.prepareStatement(update);
		// Parameters start with 1
		preparedStatement.setString(1,propuestaSocioID); 
		preparedStatement.setString(2,fechaPropuesta); 
		preparedStatement.setString(3,estado); 
		preparedStatement.setString(4,proyectoID);
		preparedStatement.setString(5,viabilidadTecnica); 
		preparedStatement.setString(6,viabilidadEconomica);
		preparedStatement.setString(7,fechaAceptacion);
		preparedStatement.setString(8,valorTotal);
		preparedStatement.setString(9,propuestaID);
		
		preparedStatement.executeUpdate();
		
		connect.close();

	}

}
