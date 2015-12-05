package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Location;
import models.User;

/**
 * @author bolat
 *
 */
public class GetClient {

	private Statement statement;
	private ResultSet resultSet;

	public GetClient(Statement statement, ResultSet resultSet) {
		this.statement = statement;
		this.resultSet = resultSet;
	}

	public User getUser(String username) {
		String query = String.format("SELECT * FROM users WHERE username='%s';",
				username);
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhoneNumber(resultSet.getString("phone"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return null;
	}

	public ArrayList<Location> getLocations(String username) {
		String query = String.format(
				"SELECT * FROM locations WHERE username='%s';", username);
		try {
			resultSet = statement.executeQuery(query);
			ArrayList<Location> locations = new ArrayList<Location>();
			while (resultSet.next()) {
				Location location = new Location();
				location.setLatitude(resultSet.getString("latitude"));
				location.setLongitude(resultSet.getString("longitude"));
				location.setTimestamp(resultSet.getString("timestamp"));
				location.setUsername(resultSet.getString("username"));
				locations.add(location);
			}
			return locations;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return null;
	}

	public ArrayList<String> getAllTrackingUsers(String username) {
		String query = String.format(
				"SELECT * FROM relations WHERE username='%s';", username);
		try {
			resultSet = statement.executeQuery(query);
			ArrayList<String> users = new ArrayList<String>();
			while (resultSet.next()) {
				users.add(resultSet.getString("tracking"));
			}
			return users;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return null;
	}

}
