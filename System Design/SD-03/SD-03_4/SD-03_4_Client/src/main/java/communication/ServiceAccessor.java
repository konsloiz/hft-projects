package communication;

import java.util.function.Supplier;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import de.stuttgart.hft.bookapp.domain.model.Author;
import de.stuttgart.hft.bookapp.domain.model.Book;
import de.stuttgart.hft.bookapp.domain.model.Genre;
import de.stuttgart.hft.bookapp.domain.model.Order;

public class ServiceAccessor {

	public static final String PATH = "http://localhost:8080/api";

	private Supplier<WebTarget> target = () -> ClientBuilder.newClient().target(PATH).path("book-app");

	public Author[] getAuthors() {
		try {
			Response response = target.get().path("authors").request().get();
			Author[] authors = response.readEntity(Author[].class);
			return authors;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Author[0];
	}
	
	public Genre[] getGenres() {
		try {
			Response response = target.get().path("genres").request().get();
			Genre[] genres = response.readEntity(Genre[].class);
			return genres;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Genre[0];
	}
	
	public Book[] getBooks() { 
		try {
			 Response response = target.get().path("books").request().get(); 
			 Book[] books = response.readEntity(Book[].class);
			 return books;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Book[0];
	}
	
	public Book[] getBooksByAuthor(Author author) { 
		try {
			 Response response = target.get().path("books").path("author") 
					 .path(Integer.toString(author.getId())).request().get(); 
			 Book[] books = response.readEntity(Book[].class);
			 return books;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Book[0];
	}
	
	public Book[] getBooksByGenre(Genre genre) { 
		try {
			 Response response = target.get().path("books").path("genre") 
					 .path(Integer.toString(genre.getId())).request().get(); 
			 Book[] books = response.readEntity(Book[].class);
			 return books;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Book[0];
	}
	
	public Book[] getBooksByAuthorAndGenre(Author author, Genre genre) {
		try {
			Response response = target.get().path("books")
					.queryParam("author", Integer.toString(author.getId()))
					.queryParam("genre", Integer.toString(genre.getId())).request().get();
			Book[] books = response.readEntity(Book[].class);
			return books;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Book[0];
	}
	
	public Book reloadBook(Book book) {
		try {
			 Response response = target.get().path("books")
					 .path(Integer.toString(book.getId())).request().get(); 
			 book = response.readEntity(Book.class);
			 return book;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;		
	}

	public void confirmOrder(Order order) { 
		 target.get().path("order").request().post(Entity.json(order)); 
	} 

}
