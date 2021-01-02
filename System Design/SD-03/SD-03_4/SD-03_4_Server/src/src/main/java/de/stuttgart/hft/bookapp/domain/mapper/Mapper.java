package de.stuttgart.hft.bookapp.domain.mapper;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public abstract class Mapper<E, D> {
	
	public abstract D map(E entity);
	
	public List<D> map(List<E> entities){
//		return entities.stream().map(e -> map(e)).collect(Collectors.toList());
		List<D> elements = new ArrayList<>();
		for(E entity : entities)
			elements.add(map(entity));
		return elements;
	}
}
