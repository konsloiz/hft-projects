package pipeline.interfaces;

import java.util.List;

public interface Pipe<T> {
	
	default boolean produces() {
		return true;
	};
	
	default boolean consumes() {
		return true;
	};
	
	default boolean isServing() {
		return true;
	};
		
	//called by the Producers
	default void put(T t) {
		throw new UnsupportedOperationException();
	};

	default void put(List<T> ts) {
		for(T t : ts) put(t);
	};

	//called by the Consumers
	default T get() {
		throw new UnsupportedOperationException();
	};
	
	default List<T> get(int n) {
		throw new UnsupportedOperationException();
	};

	default List<T> getAll() {
		throw new UnsupportedOperationException();
	};

	default List<T> getAll(int n) {
		throw new UnsupportedOperationException();
	};
}