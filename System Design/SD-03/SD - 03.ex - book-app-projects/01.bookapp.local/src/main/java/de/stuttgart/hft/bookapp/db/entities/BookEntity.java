package de.stuttgart.hft.bookapp.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "BOOKS")
public class BookEntity {

	public static final String ID = "ID";
	public static final String AUTHOR_ID = "AUTHOR_ID";
	public static final String GENRE_ID = "GENRE_ID";
	public static final String TITLE = "TITLE";
	public static final String PUB_YEAR = "PUB_YEAR";
	public static final String SAMPLE = "SAMPLE";
	
	@DatabaseField(columnName = ID, generatedId = true) private int id;	
	@DatabaseField(columnName = AUTHOR_ID, canBeNull = false, foreign = true, foreignAutoRefresh = true) private AuthorEntity author;
	@DatabaseField(columnName = GENRE_ID, canBeNull = false, foreign = true, foreignAutoRefresh = true) private GenreEntity genre;
	@DatabaseField(columnName = TITLE) private String title;
	@DatabaseField(columnName = PUB_YEAR) private int year;
	@DatabaseField(columnName = SAMPLE, dataType=DataType.LONG_STRING) private String sample;
	
	public BookEntity() {}

	public int getId() {
		return id;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getSample() {
		return sample;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", author=" + author + ", genre=" + genre + ", title=" + title + ", sample="
				+ (sample == null || sample.length() <= 10 ? sample : sample.substring(0,  10) + "...") 
				+ ", year=" + year + "]";
	}
}
