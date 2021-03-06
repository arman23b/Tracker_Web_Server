DROP TABLE users;
DROP TABLE relations;
DROP TABLE locations;

CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(25) NOT NULL PRIMARY KEY,
	name VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	email VARCHAR(25),
	phone VARCHAR(25));

CREATE TABLE IF NOT EXISTS relations(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(25) NOT NULL,
	tracking VARCHAR(25) NOT NULL,
	approved INT NOT NULL);
	
CREATE TABLE IF NOT EXISTS locations(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	latitude VARCHAR(25) NOT NULL, 
	longitude VARCHAR(25) NOT NULL,
	timestamp VARCHAR(25) NOT NULL,
	username VARCHAR(25) NOT NULL);
