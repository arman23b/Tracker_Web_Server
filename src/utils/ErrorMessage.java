package utils;

import org.json.simple.JSONObject;

public class ErrorMessage {

	public enum Type {
		USER_NOT_FOUND, USERNAME_MISSING, PASSWORD_MISSING, NAME_MISSING, 
		EMAIL_MISSING, PHONE_MISSING, LATITUDE_MISSING, LONGITUDE_MISSING, 
		TIMESTAMP_MISSING, DATA_MISSING, DB_ERROR, NO_ERROR
	}

	public ErrorMessage() {
	}

	@SuppressWarnings("unchecked")
	public static String print(Type type) {
		JSONObject json = new JSONObject();
		String key = "error";
		switch (type) {
		case LATITUDE_MISSING:
			json.put(key, "Latitude is missing");
			break;
		case LONGITUDE_MISSING:
			json.put(key, "Longitude is missing");
			break;
		case TIMESTAMP_MISSING:
			json.put(key, "Timestamp is missing");
			break;
		case USERNAME_MISSING:
			json.put(key, "Username is missing");
			break;
		case USER_NOT_FOUND:
			json.put(key, "User is not found");
			break;
		case EMAIL_MISSING:
			json.put(key, "Email is missing");
			break;
		case NAME_MISSING:
			json.put(key, "Name is missing");
			break;
		case PASSWORD_MISSING:
			json.put(key, "Password is missing");
			break;
		case PHONE_MISSING:
			json.put(key, "Phone number is missing");
			break;
		case DATA_MISSING:
			json.put(key, "Data is missing");
			break;
		case DB_ERROR:
			json.put(key, "Database Error");
			break;
		case NO_ERROR:
			json.put(key, "None");
			break;
		default:
			break;
		}
		return json.toJSONString();
	}

}
