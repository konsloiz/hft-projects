package de.stuttgart.hft.bookapp.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "AUTHORS")
public class AuthorEntity {
		
	public static final String ID = "ID";
	public static final String FIRSTNAME = "FIRSTNAME";
	public static final String LASTNAME = "LASTNAME";

	@DatabaseField(columnName = ID, generatedId = true) private int id;
	@DatabaseField(columnName = FIRSTNAME) private String firstname;
	@DatabaseField(columnName = LASTNAME) private String lastname;

	public AuthorEntity() {}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "AuthorEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
}
