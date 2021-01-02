package de.stuttgart.hft.bookapp.domain.model;

public class Book{

	private int id;	
	private Author author;
	private Genre genre;
	private String title;
	private int year;
	private String sample;

	// Required by JSON
	public Book() {}

	// Required for Mapper
	public Book(int id, Author author, Genre genre, String title, int year, String sample) {
		this.id = id;
		this.author = author;
		this.genre = genre;
		this.title = title;
		this.year = year;
		this.sample = sample;
	}

	public int getId() {
		return id;
	}

	public Author getAuthor() {
		return author;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}
	
	public String getSample() {
		return sample;
	}
	
	public void setSample(String sample) {
		this.sample = sample;
	}

	@Override
	public String toString() {
		return title + " (" + year + ")";
	}
}
