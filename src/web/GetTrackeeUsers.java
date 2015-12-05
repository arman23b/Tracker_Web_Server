package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import database.DatabaseClient;
import models.User;
import utils.ErrorMessage;

/**
 * Servlet implementation class GetTrackeeUsers
 */
@WebServlet("/get_trackee_users")
public class GetTrackeeUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseClient dbClient;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		if (username != null) {
			this.dbClient = new DatabaseClient();
			if (User.userExists(username)) {
				ArrayList<String> trackeeUsers = User
						.getAllTrackeeUsers(username);
				if (trackeeUsers != null) {
					JSONArray jsonArray = new JSONArray();
					for (String trackingUsername : trackeeUsers) {
						jsonArray.add(trackingUsername);
					}
					response.getWriter().print(jsonArray.toJSONString());
				} else {
					response.getWriter().print(
							ErrorMessage.print(ErrorMessage.Type.DB_ERROR));
				}
			} else {
				response.getWriter().print(
						ErrorMessage.print(ErrorMessage.Type.USER_NOT_FOUND));
			}
			this.dbClient.closeConnection();
		} else {
			response.getWriter().print(
					ErrorMessage.print(ErrorMessage.Type.USERNAME_MISSING));
		}
	}

}
