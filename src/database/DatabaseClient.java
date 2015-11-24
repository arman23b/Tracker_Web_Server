package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;

/**
 * The main database client responsible for the communication with the database
 */
public class DatabaseClient {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private CreateClient createClient;
	private GetClient getClient;

	public DatabaseClient(String url, String user, String password) {
		// Open db connection
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			createClient = new CreateClient(statement, resultSet);
			getClient = new GetClient(statement, resultSet);
			System.out.println(this.getClient);
		} catch (SQLException e) {
			System.err
					.println("Cannot connect to the database: " + e.toString());
		}
	}

	public boolean createUser(User user) {
		return this.createClient.createUser(user);
	}

	public User getUser(String username) {
		return this.getClient.getUser(username);
	}

	public void closeConnection() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
	}

}
