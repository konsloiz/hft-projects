CREATE TABLE GENRES (
	ID INT NOT NULL, 
	GENRE_NAME VARCHAR(30), 
	PRIMARY KEY (ID)
);

CREATE TABLE AUTHORS (
	ID INT NOT NULL, 
	FIRSTNAME VARCHAR(30), 
	LASTNAME VARCHAR(30),
	PRIMARY KEY (ID)
);

CREATE TABLE BOOKS (
	ID INT NOT NULL, 
	TITLE VARCHAR(50), 
	PUB_YEAR INT,
	AUTHOR_ID INT,
	GENRE_ID INT,
	SAMPLE LONG VARCHAR,
	PRIMARY KEY (ID),
	FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS,
	FOREIGN KEY (GENRE_ID) REFERENCES GENRES
);