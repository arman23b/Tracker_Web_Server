package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import database.DatabaseClient;
import models.Location;
import models.User;
import utils.ErrorMessage;

/**
 * Servlet implementation class AddLocation
 */
@WebServlet("/add_location")
public class AddLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseClient dbClient;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String body = request.getParameter("data");
		response.setStatus(HttpServletResponse.SC_OK);
		if (body != null) {
			JSONObject obj = (JSONObject) JSONValue.parse(body);
			String username = (String) obj.get("username");
			String latitude = (String) obj.get("latitude");
			String longitude = (String) obj.get("longitude");
			String timestamp = (String) obj.get("timestamp");
			Location location = new Location();
			if (username != null) {
				this.dbClient = new DatabaseClient();
				if (!User.userExists(username)) {
					response.getWriter().println(ErrorMessage
							.print(ErrorMessage.Type.USER_NOT_FOUND));
					this.dbClient.closeConnection();
					return;
				}
			} else {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.USERNAME_MISSING));
				return;
			}
			if (latitude == null) {
				response.getWriter().println(
						ErrorMessage.print(ErrorMessage.Type.LATITUDE_MISSING));
				this.dbClient.closeConnection();
				return;
			}
			if (longitude == null) {
				response.getWriter().println(ErrorMessage
						.print(ErrorMessage.Type.LONGITUDE_MISSING));
				this.dbClient.closeConnection();
				return;
			}
			if (timestamp == null) {
				response.getWriter().println(ErrorMessage
						.print(ErrorMessage.Type.TIMESTAMP_MISSING));
				this.dbClient.closeConnection();
				return;
			}
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setTimestamp(timestamp);
			location.setUsername(username);
			Location.addLocation(location);
			this.dbClient.closeConnection();
			response.getWriter()
					.println(ErrorMessage.print(ErrorMessage.Type.NO_ERROR));
		} else {
			response.getWriter().println(
					ErrorMessage.print(ErrorMessage.Type.DATA_MISSING));
		}
	}

}
