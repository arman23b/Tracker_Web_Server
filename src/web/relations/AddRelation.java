package web.relations;

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
 * Servlet implementation class AddRelation
 */
@WebServlet("/add_relation")
public class AddRelation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseClient dbClient;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String body = request.getParameter("data");
		response.setStatus(HttpServletResponse.SC_OK);
		if (body != null) {
			JSONObject obj = (JSONObject) JSONValue.parse(body);
			String username = (String) obj.get("username");
			String trackingUsername = (String) obj.get("tracking");
			if (username == null || trackingUsername == null) {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.USERNAME_MISSING));
				return;
			}
			this.dbClient = new DatabaseClient();
			if (User.userExists(username)
					&& User.userExists(trackingUsername)) {
				User.addRelation(username, trackingUsername);
			} else {
				this.dbClient.closeConnection();
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.USER_NOT_FOUND));
				return;
			}
			this.dbClient.closeConnection();
			response.getWriter()
					.println(ErrorMessage.print(ErrorMessage.Type.NO_ERROR));
		} else {
			response.getWriter().println(
					ErrorMessage.print(ErrorMessage.Type.DATA_MISSING));
		}
	}

}
