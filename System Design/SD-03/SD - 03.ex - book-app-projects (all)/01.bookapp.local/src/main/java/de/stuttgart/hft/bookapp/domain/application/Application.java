package de.stuttgart.hft.bookapp.domain.application;

import java.util.List;

import de.stuttgart.hft.bookapp.db.access.DBAccess;
import de.stuttgart.hft.bookapp.domain.model.Author;
import de.stuttgart.hft.bookapp.domain.model.Book;
import de.stuttgart.hft.bookapp.domain.model.Genre;
import de.stuttgart.hft.bookapp.domain.model.Order;
import de.stuttgart.hft.bookapp.domain.util.GoogleMailAccess;

public class Application {
	
	private DBAccess db = new DBAccess();
	private GoogleMailAccess mailer = new GoogleMailAccess();
	
	public List<Author> getAuthors(){
		return db.getAuthors();
	}
	
	public List<Genre> getGenres(){
		return db.getGenres();
	}
	
	public List<Book> getBooks(){
		return db.getBooks();
	}
	
	public Book getBook(int bookId) {
		return db.getBook(bookId);
	}
	
	public List<Book> getBooksByAuthor(Author author) {
		return db.getBooksByAuthor(author.getId());
	}

	public List<Book> getBooksByAuthor(int authorId) {
		return db.getBooksByAuthor(authorId);
	}

	public List<Book> getBooksByGenre(Genre genre) {
		return db.getBooksByGenre(genre.getId());
	}

	public List<Book> getBooksByGenre(int genreId) {
		return db.getBooksByGenre(genreId);
	}

	public List<Book> getBooksByAuthorAndGenre(Author author, Genre genre) {
		return db.getBooksByAuthorAndGenre(author.getId(), genre.getId());
	}

	public List<Book> getBooksByAuthorAndGenre(int authorId, int genreId) {
		return db.getBooksByAuthorAndGenre(authorId, genreId);
	}

	public void confirmOrder(Order order) {
		
		String name = order.getName();
		String recipientEmail = order.getEmail();
		String address = order.getAddress();
		Book book = this.getBook(order.getBookId());
		
		String message = "Dear " + name + ",\nthis is a confirmation of your order of '"
				+ book.getTitle() + "' by " + book.getAuthor() +".\n\n" 
				+ "It will be sent to: " + address + ".\n\n"
				+ "Thanks for ordering at the Book-App-Shop.";
		
		mailer.send(recipientEmail, "Order Confirmation: '" + book.getTitle() + "'", message);
	}
}
