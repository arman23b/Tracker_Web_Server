package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author bolat
 *
 */
public class DeleteClient {

	private Statement statement;

	public DeleteClient(Statement statement) {
		this.statement = statement;
	}

	public boolean deleteUser(String username) {
		String query1 = String.format("DELETE FROM users WHERE username='%s';",
				username);
		String query2 = String.format("DELETE FROM locations WHERE username='%s';",
				username);
		String query3 = String.format("DELETE FROM relations WHERE username='%s';",
				username);
		String query4 = String.format("DELETE FROM relations WHERE tracking='%s';",
				username);
		try {
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
			return true;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return false;
	}

}
