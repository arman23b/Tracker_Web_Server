package models;

import database.DatabaseClient;

/**
 * @author bolat
 *
 */
public class Location {
	
	private String latitude;
	private String longitude;
	private String timestamp;
	private String username;
	
	private static DatabaseClient dbClient;
	
	public Location() {
	}
	
	public static void setDbClient(DatabaseClient dbClient) {
		Location.dbClient = dbClient;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public static boolean addLocation(Location location) {
		return Location.dbClient.createLocation(location);
	}

}
