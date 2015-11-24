package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class GetUser
 */
@WebServlet("/users")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		if (username != null) {
			User user = User.getUser(username);
			if (user != null) {
				response.getWriter().print(user.toJSONString());
			} else {
				response.getWriter().print(
						"User with username " + username + " is not found");
			}
		} else {
			response.getWriter().print("No username provided");
		}
	}

}
