package de.stuttgart.hft.bookapp.domain.mapper;

import de.stuttgart.hft.bookapp.domain.model.Book;
import de.stuttgart.hft.bookapp.db.entities.BookEntity;

public class BookMapper extends Mapper<BookEntity, Book> {
	
	private static final AuthorMapper AM = new AuthorMapper();
	private static final GenreMapper GM = new GenreMapper();
	

	@Override
	public Book map(BookEntity entity) {
		return new Book(entity.getId(), 
				AM.map(entity.getAuthor()), GM.map(entity.getGenre()),
				entity.getTitle(), entity.getYear(), entity.getSample());
	}

}
