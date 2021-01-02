package de.stuttgart.hft.bookapp.db.access;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import de.stuttgart.hft.bookapp.db.entities.AuthorEntity;
import de.stuttgart.hft.bookapp.db.entities.BookEntity;
import de.stuttgart.hft.bookapp.db.entities.GenreEntity;
import de.stuttgart.hft.bookapp.domain.mapper.AuthorMapper;
import de.stuttgart.hft.bookapp.domain.mapper.BookMapper;
import de.stuttgart.hft.bookapp.domain.mapper.GenreMapper;
import de.stuttgart.hft.bookapp.domain.model.Author;
import de.stuttgart.hft.bookapp.domain.model.Book;
import de.stuttgart.hft.bookapp.domain.model.Genre;

public class DBAccess {

	private static final String DB_URL = "jdbc:derby:../00.database/db;create=false";

	private static final AuthorMapper AUTHOR_MAPPER = new AuthorMapper();
	private static final GenreMapper GENRE_MAPPER = new GenreMapper();
	private static final BookMapper BOOK_MAPPER = new BookMapper();

	public List<Author> getAuthors() {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){
			Dao<AuthorEntity, Integer> authorDao = 
				DaoManager.createDao(connectionSource, AuthorEntity.class);
			List<AuthorEntity> entities = authorDao.queryBuilder()
				.orderBy(AuthorEntity.LASTNAME, true)
				.orderBy(AuthorEntity.FIRSTNAME, true).query();
	      	List<Author> authors = AUTHOR_MAPPER.map(entities);
			return authors;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Author>();
	}

	public List<Genre> getGenres() {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){
	        Dao<GenreEntity, Integer> genreDao = DaoManager.createDao(connectionSource, GenreEntity.class);	        
	        List<GenreEntity> entities = genreDao.queryBuilder().
	        		orderBy(GenreEntity.GENRE_NAME, true).query();        	
	      	List<Genre> genres = GENRE_MAPPER.map(entities);
	        return genres;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Genre>();
	}

	public List<Book> getBooks() {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){
	        Dao<BookEntity, Integer> bookDao = DaoManager.createDao(connectionSource, BookEntity.class);	        
	        List<BookEntity> entities = bookDao.queryForAll();
//	        List<BookEntity> entities = bookDao.queryBuilder().
//	        		orderBy(BookEntity.TITLE, true).query();        	
	      	List<Book> books = BOOK_MAPPER.map(entities);
			return books;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Book>();
	}
	
	// =============== additional functionality ===============
	
	public List<Book> getBooksByAuthor(int authorId) {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){	        
	        Dao<AuthorEntity, Integer> authorDao = DaoManager.createDao(connectionSource, AuthorEntity.class);	        
	        Dao<BookEntity, Integer> bookDao = DaoManager.createDao(connectionSource, BookEntity.class);	        

	        QueryBuilder<AuthorEntity, Integer> authorQb = authorDao.queryBuilder();
	        QueryBuilder<BookEntity, Integer> bookQb = bookDao.queryBuilder();
	        
	        authorQb.where().eq(AuthorEntity.ID, authorId);
        	List<BookEntity> entities = bookQb.join(authorQb)
        			.orderBy(BookEntity.TITLE, true).query();
	
	        List<Book> books = BOOK_MAPPER.map(entities);
	        return books;
			} catch (IOException | SQLException e) {
				e.printStackTrace();
		}
		return new ArrayList<Book>();
	}

	public List<Book> getBooksByGenre(int genreId) {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){	        
	        Dao<GenreEntity, Integer> genreDao = DaoManager.createDao(connectionSource,GenreEntity.class);	        
	        Dao<BookEntity, Integer> bookDao = DaoManager.createDao(connectionSource, BookEntity.class);	        

	        QueryBuilder<GenreEntity, Integer> genreQb = genreDao.queryBuilder();
	        QueryBuilder<BookEntity, Integer> bookQb = bookDao.queryBuilder();
	        
	        genreQb.where().eq(GenreEntity.ID, genreId);
        	List<BookEntity> entities = bookQb.join(genreQb)
        			.orderBy(BookEntity.TITLE, true).query();
	
	        List<Book> books = BOOK_MAPPER.map(entities);
	        return books;
			} catch (IOException | SQLException e) {
				e.printStackTrace();
		}
		return new ArrayList<Book>();
	}

	public List<Book> getBooksByAuthorAndGenre(int authorId, int genreId) {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){	        
	        Dao<GenreEntity, Integer> genreDao		= DaoManager.createDao(connectionSource,GenreEntity.class);	        
	        Dao<AuthorEntity, Integer> authorDao	= DaoManager.createDao(connectionSource, AuthorEntity.class);	        
	        Dao<BookEntity, Integer> bookDao		= DaoManager.createDao(connectionSource, BookEntity.class);	        

	        QueryBuilder<GenreEntity, Integer> genreQb		= genreDao.queryBuilder();
	        QueryBuilder<AuthorEntity, Integer> authorQb	= authorDao.queryBuilder();
	        QueryBuilder<BookEntity, Integer> bookQb		= bookDao.queryBuilder();
	        
	        genreQb.where().eq(GenreEntity.ID, genreId);
	        authorQb.where().eq(AuthorEntity.ID, authorId);
        	List<BookEntity> entities = bookQb.join(authorQb).join(genreQb)
        			.orderBy(BookEntity.TITLE, true).query();
	
	        List<Book> books = BOOK_MAPPER.map(entities);
	        return books;
			} catch (IOException | SQLException e) {
				e.printStackTrace();
		}
		return new ArrayList<Book>();
	}

	public Book getBook(int bookId) {
		try(ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL)){	        
	        Dao<BookEntity, Integer> bookDao = DaoManager.createDao(connectionSource, BookEntity.class);	        
	        BookEntity entity = bookDao.queryForId(bookId);
        	if(entity != null)
        		return BOOK_MAPPER.map(entity);        	
			} catch (IOException | SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		
		// Remove Debug-Info
		System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");

		DBAccess db = new DBAccess();
		
		System.out.println("============= Authors =============");
		List<Author> authors = db.getAuthors();
    	for(Author entity : authors)
    		System.out.println(entity);
		System.out.println("===================================\n");

		System.out.println("============= Genres =============");
		List<Genre> genres = db.getGenres();
    	for(Genre entity : genres)
    		System.out.println(entity);
		System.out.println("===================================\n");

		System.out.println("============= Books =============");
		List<Book> books = db.getBooks();
    	for(Book entity : books)
    		System.out.println(entity);
		System.out.println("===================================\n");

		System.out.println("============= Books by Author =============");
		books = db.getBooksByAuthor(1);
    	for(Book entity : books)
    		System.out.println(entity);
		System.out.println("===================================\n");
	
		System.out.println("======== Books by Genre ========");
		books = db.getBooksByGenre(1);
    	for(Book entity : books)
    		System.out.println(entity);
		System.out.println("===================================\n");
	
		System.out.println("====== Books by Author/Genre ======");
		books = db.getBooksByAuthorAndGenre(1, 3);
    	for(Book entity : books)
    		System.out.println(entity);
		System.out.println("===================================\n");
	
		System.out.println("=========== Single Book ===========");
		Book book = db.getBook(1);
    	System.out.println(book);

    	book = db.getBook(10000);
    	System.out.println(book);
		System.out.println("===================================\n");
	
	}
}
