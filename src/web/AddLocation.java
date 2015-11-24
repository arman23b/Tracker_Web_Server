package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import models.Location;
import models.User;

/**
 * Servlet implementation class AddLocation
 */
@WebServlet("/add_location")
public class AddLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			User user;
			if (username != null) {
				user = User.getUser(username);
				if (user == null) {
					response.getWriter().println("{'error' : 'No username found'}");
					return;
				}
			} else {
				response.getWriter().println("{'error' : 'Missing username'}");
				return;
			}
			if (latitude == null) {
				response.getWriter().println("{'error' : 'Missing latitude'}");
				return;
			}
			if (longitude == null) {
				response.getWriter().println("{'error' : 'Missing longitude'}");
				return;
			}
			if (timestamp == null) {
				response.getWriter().println("{'error' : 'Missing timestamp'}");
				return;
			}
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setTimestamp(timestamp);
			user.addLocation(location);
			response.getWriter().println("{'error' : 'Location added'}");
		} else {
			response.getWriter().println("{'error' : 'No data parameter'}");
		}
	}

}
