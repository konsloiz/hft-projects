package de.stuttgart.hft.bookapp.domain.mapper;

import de.stuttgart.hft.bookapp.domain.model.Author;
import de.stuttgart.hft.bookapp.db.entities.AuthorEntity;

public class AuthorMapper extends Mapper<AuthorEntity, Author> {

	@Override
	public Author map(AuthorEntity entity) {
		return new Author(entity.getId(), entity.getFirstname(), entity.getLastname());
	}
}
