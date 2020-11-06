package de.stuttgart.hft.bookapp.domain.mapper;

import de.stuttgart.hft.bookapp.domain.model.Genre;
import de.stuttgart.hft.bookapp.db.entities.GenreEntity;

public class GenreMapper extends Mapper<GenreEntity, Genre> {

	@Override
	public Genre map(GenreEntity entity) {
		return new Genre(entity.getId(), entity.getGenreName(), true);
	}

}
