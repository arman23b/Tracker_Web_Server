package models;

import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * @author bolat
 *
 */
public class User {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ArrayList<String> trackingUsers = new ArrayList<String>();
	private ArrayList<String> trackeeUsers = new ArrayList<String>();
	
	private static ArrayList<User> users = new ArrayList<User>();

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<String> getTrackingUsers() {
		return trackingUsers;
	}

	public void setTrackingUsers(ArrayList<String> trackingUsers) {
		this.trackingUsers = trackingUsers;
	}

	public ArrayList<String> getTrackeeUsers() {
		return trackeeUsers;
	}

	public void setTrackeeUsers(ArrayList<String> trackeeUsers) {
		this.trackeeUsers = trackeeUsers;
	}
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
	
	public String toJSONString() {
		JSONObject json = new JSONObject();
		json.put("username", this.username);
		json.put("password", this.password);
		json.put("name", this.name);
		json.put("email", this.email);
		json.put("phone_number", this.phoneNumber);
		json.put("locations", this.locations.toString());
		json.put("tracking", this.trackingUsers.toString());
		json.put("trackee", this.trackeeUsers.toString());
		return json.toJSONString();
	}
	
	public static User getUser(String username) {
		for (User user : User.users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public static boolean addUser(User user) {
		User.users.add(user);
		return true;
	}

}
