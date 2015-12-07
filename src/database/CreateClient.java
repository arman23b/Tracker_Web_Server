package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Location;
import models.User;

/**
 * @author bolat
 *
 */
public class CreateClient {

	private Statement statement;
	private ResultSet resultSet;

	public CreateClient(Statement statement, ResultSet resultSet) {
		this.statement = statement;
		this.resultSet = resultSet;
	}

	public boolean createUser(User user) {
		if (user != null && !this.userExists(user.getUsername())) {
			String query = String.format(
					"INSERT INTO users VALUES('%s', '%s', '%s', '%s', '%s');",
					user.getUsername(), user.getName(), user.getPassword(),
					user.getEmail(), user.getPhoneNumber());
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
			return true;
		}
		return false;
	}

	public boolean createLocation(Location location) {
		if (location != null) {
			String query = String.format(
					"INSERT INTO locations(latitude, longitude, timestamp, username)"
							+ " VALUES('%s', '%s', '%s', '%s');",
					location.getLatitude(), location.getLongitude(),
					location.getTimestamp(), location.getUsername());
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
			return true;
		}
		return false;
	}

	public boolean createRelation(String username, String trackingUsername) {
		if (!this.relationExists(username, trackingUsername)) {
			String query = String.format(
					"INSERT INTO relations(username, tracking, approved)"
							+ " VALUES('%s', '%s', %d);",
					username, trackingUsername, 0);
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
			return true;
		}
		return false;
	}

	public boolean userExists(String username) {
		String query = String.format("SELECT * FROM users WHERE username='%s';",
				username);
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return false;
	}

	public boolean relationExists(String username, String trackingUsername) {
		String query = String.format(
				"SELECT * FROM relations WHERE username='%s' and tracking='%s';",
				username, trackingUsername);
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return false;
	}

}
