package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author bolat
 *
 */
public class UpdateClient {

	private Statement statement;

	public UpdateClient(Statement statement) {
		this.statement = statement;
	}

	public boolean updateRelation(String username, String trackingUsername,
			int approved) {
		String query = String.format(
				"UPDATE relations SET approved=%d WHERE username='%s' and tracking='%s';",
				approved, username, trackingUsername);
		try {
			statement.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return false;
	}

}
