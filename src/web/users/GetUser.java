package web.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseClient;
import models.User;
import utils.ErrorMessage;

/**
 * Servlet implementation class GetUser
 */
@WebServlet("/users")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseClient dbClient;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		if (username != null) {
			this.dbClient = new DatabaseClient();
			User user = User.getUser(username);
			this.dbClient.closeConnection();
			if (user != null) {
				response.getWriter().print(user.toJSONString());
			} else {
				response.getWriter().print(
						ErrorMessage.print(ErrorMessage.Type.USER_NOT_FOUND));
			}
		} else {
			response.getWriter().print(
					ErrorMessage.print(ErrorMessage.Type.USERNAME_MISSING));
		}
	}

}
