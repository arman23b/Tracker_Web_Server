package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Location;
import models.User;

/**
 * The main database client responsible for the communication with the database
 */
public class DatabaseClient {

	private final static String URL = "jdbc:mysql://localhost:3306/tracker";
	private final static String USER = "bolat";
	private final static String PASSWORD = "password_18641";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private CreateClient createClient;
	private GetClient getClient;
	private DeleteClient deleteClient;
	private UpdateClient updateClient;

	public DatabaseClient() {
		// Open db connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
			createClient = new CreateClient(statement, resultSet);
			getClient = new GetClient(statement, resultSet);
			deleteClient = new DeleteClient(statement);
			updateClient = new UpdateClient(statement);
			// Set dbClients in models
			User.setDbClient(this);
			Location.setDbClient(this);
		} catch (SQLException e) {
			System.err
					.println("Cannot connect to the database: " + e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean createUser(User user) {
		return this.createClient.createUser(user);
	}

	public boolean userExists(String username) {
		return this.createClient.userExists(username);
	}

	public User getUser(String username) {
		return this.getClient.getUser(username);
	}

	public boolean createLocation(Location location) {
		return this.createClient.createLocation(location);
	}

	public ArrayList<Location> getLocations(String username) {
		return this.getClient.getLocations(username);
	}

	public boolean relationExists(String username, String trackingUsername) {
		return this.createClient.relationExists(username, trackingUsername);
	}

	public boolean createRelation(String username, String trackingUsername) {
		return this.createClient.createRelation(username, trackingUsername);
	}

	public ArrayList<String> getAllTrackingUsers(String username) {
		return this.getClient.getAllTrackingUsers(username);
	}

	public ArrayList<String> getAllTrackeeUsers(String username) {
		return this.getClient.getAllTrackeeUsers(username);
	}

	public ArrayList<String> getAllRequests(String username) {
		return this.getClient.getAllRequests(username);
	}

	public boolean deleteUser(String username) {
		return this.deleteClient.deleteUser(username);
	}

	public boolean deleteRelation(String username, String trackingUsername) {
		return this.deleteClient.deleteRelation(username, trackingUsername);
	}
	
	public boolean updateRelation(String username, String trackingUsername, int approved) {
		return this.updateClient.updateRelation(username, trackingUsername, approved);
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
