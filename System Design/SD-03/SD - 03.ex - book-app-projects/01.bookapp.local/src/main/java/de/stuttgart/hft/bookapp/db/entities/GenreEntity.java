package de.stuttgart.hft.bookapp.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "GENRES")
public class GenreEntity {

	public static final String ID = "ID";
	public static final String GENRE_NAME = "GENRE_NAME";
	
	@DatabaseField(columnName = ID, generatedId = true) private int id;
	@DatabaseField(columnName = GENRE_NAME) private String genreName;

	public GenreEntity() {}

	public int getId() {
		return id;
	}

	public String getGenreName() {
		return genreName;
	}

	@Override
	public String toString() {
		return "GenreEntity [id=" + id + ", genreName=" + genreName + "]";
	}
}
