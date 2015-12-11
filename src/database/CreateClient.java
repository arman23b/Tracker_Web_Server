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
	
	private static final int MAX_ROWS = 100;

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
			int rowCount = this.getRowsCount("locations");
			if (rowCount >= MAX_ROWS) {
				this.deleteFirstNLocations(rowCount - MAX_ROWS + 1);
			}
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

	private int getRowsCount(String table) {
		String query = String.format("SELECT COUNT(*) FROM %s;", table);
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int count = Integer.parseInt(resultSet.getString(1));
				return count;
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return -1;
	}

	private boolean deleteFirstNLocations(int n) {
		if (n >= 1) {
			String query = String.format(
					"DELETE FROM locations ORDER BY id ASC LIMIT %d;", n);
			try {
				statement.executeUpdate(query);
				return true;
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
			return false;
		}
		return false;
	}

}
