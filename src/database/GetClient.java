package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				user.setPhoneNumber(resultSet.getString("phone_number"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return null;
	}

}
