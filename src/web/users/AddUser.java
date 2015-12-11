package web.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import database.DatabaseClient;
import models.User;
import utils.ErrorMessage;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/add_user")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseClient dbClient;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String body = request.getParameter("data");
		response.setStatus(HttpServletResponse.SC_OK);
		if (body != null) {
			JSONObject obj = (JSONObject) JSONValue.parse(body);
			User user = new User();
			String username = (String) obj.get("username");
			String password = (String) obj.get("password");
			String name = (String) obj.get("name");
			String email = (String) obj.get("email");
			String phoneNumber = (String) obj.get("phone_number");
			if (username != null) {
				this.dbClient = new DatabaseClient();
				boolean userExists = User.userExists(username);
				this.dbClient.closeConnection();
				if (userExists) {
					response.getWriter().println(
							ErrorMessage.print(ErrorMessage.Type.USER_EXISTS));
					return;
				}
				user.setUsername(username);
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.USERNAME_MISSING));
				return;
			}
			if (password != null) {
				user.setPassword(password);
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.PASSWORD_MISSING));
				return;
			}
			if (name != null) {
				user.setName(name);
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.NAME_MISSING));
				return;
			}
			if (email != null) {
				user.setEmail(email);
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.EMAIL_MISSING));
				return;
			}
			if (phoneNumber != null) {
				user.setPhoneNumber(phoneNumber);
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.PHONE_MISSING));
				return;
			}
			this.dbClient = new DatabaseClient();
			User.addUser(user);
			this.dbClient.closeConnection();
			response.getWriter()
					.println(ErrorMessage.print(ErrorMessage.Type.NO_ERROR));
		} else {
			response.getWriter().println(
					ErrorMessage.print(ErrorMessage.Type.DATA_MISSING));
		}
	}

}
