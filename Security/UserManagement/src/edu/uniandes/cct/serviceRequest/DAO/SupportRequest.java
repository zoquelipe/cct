package edu.uniandes.cct.serviceRequest.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupportRequest {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase(String ticketNumber, String description) throws Exception {
            try {
                   
            	// This will load the MySQL driver, each DB has its own driver
                    Class.forName("com.mysql.jdbc.Driver");
                    // Setup the connection with the DB
                    connect = DriverManager
                                    .getConnection("jdbc:mysql://localhost/service_desk?"
                                                    + "useSSL=true&user=sqluser&password=sqluserpw");

                    // Statements allow to issue SQL queries to the database
                    statement = connect.createStatement();
                    // PreparedStatements can use variables and are more efficient
                    preparedStatement = connect.prepareStatement("insert into  service_desk.service_request values (default, ?, ?)");
                    // Parameters start with 1
                    preparedStatement.setString(1, ticketNumber);
                    preparedStatement.setString(2, description);
                    preparedStatement.executeUpdate();
                    preparedStatement = connect.prepareStatement("SELECT id, TICKET_NUMBER, DESCRIPTION from service_desk.service_request");
                    resultSet = preparedStatement.executeQuery();
                    writeResultSet(resultSet);

            } catch (Exception e) {
                    throw e;
            } finally {
                    close();
            }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
            // ResultSet is initially before the first data set
            while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String ticketNumber = resultSet.getString("TICKET_NUMBER");
                    String description = resultSet.getString("DESCRIPTION");
                    System.out.println("id: " + id);
                    System.out.println("ticketNumber: " + ticketNumber);
                    System.out.println("description: " + description);
            }
    }

    // You need to close the resultSet
    private void close() {
            try {
                    if (resultSet != null) {
                            resultSet.close();
                    }

                    if (statement != null) {
                            statement.close();
                    }

                    if (connect != null) {
                            connect.close();
                    }
            } catch (Exception e) {

            }
    }
}
