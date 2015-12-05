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
 * Servlet implementation class DeleteUser
 */
@WebServlet("/delete_user")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseClient dbClient;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		if (username != null) {
			this.dbClient = new DatabaseClient();
			if (User.userExists(username)) {
				User.deleteUser(username);
				response.getWriter().print(
						ErrorMessage.print(ErrorMessage.Type.NO_ERROR));
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
