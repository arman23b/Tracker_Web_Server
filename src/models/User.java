package models;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import database.DatabaseClient;

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

	private static DatabaseClient dbClient;

	public User() {
	}

	public static void setDbClient(DatabaseClient dbClient) {
		User.dbClient = dbClient;
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

	@SuppressWarnings("unchecked")
	public String toJSONString() {
		JSONObject json = new JSONObject();
		json.put("username", this.username);
		json.put("password", this.password);
		json.put("name", this.name);
		json.put("email", this.email);
		json.put("phone_number", this.phoneNumber);
		json.put("error", "None");
		return json.toJSONString();
	}

	public static boolean userExists(String username) {
		return User.dbClient.userExists(username);
	}

	public static User getUser(String username, String phone) {
		return User.dbClient.getUser(username, phone);
	}

	public static boolean addUser(User user) {
		return User.dbClient.createUser(user);
	}

	public static boolean relationExists(String username,
			String trackingUsername) {
		return User.dbClient.relationExists(username, trackingUsername);
	}

	public static boolean addRelation(String username,
			String trackingUsername) {
		return User.dbClient.createRelation(username, trackingUsername);
	}

	public static ArrayList<String> getAllTrackingUsers(String username) {
		return User.dbClient.getAllTrackingUsers(username);
	}

	public static ArrayList<String> getAllTrackeeUsers(String username) {
		return User.dbClient.getAllTrackeeUsers(username);
	}

	public static ArrayList<String> getAllRequests(String username) {
		return User.dbClient.getAllRequests(username);
	}

	public static boolean deleteUser(String username) {
		return User.dbClient.deleteUser(username);
	}

	public static boolean deleteRelation(String username,
			String trackingUsername) {
		return User.dbClient.deleteRelation(username, trackingUsername);
	}

	public static boolean updateRelation(String username,
			String trackingUsername, int approved) {
		return User.dbClient.updateRelation(username, trackingUsername,
				approved);
	}

}
