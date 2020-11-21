package pipeline.interfaces;

import java.util.List;

@FunctionalInterface
public interface Reducer <S, T> {
	
	T reduce(List<S> s);
	
}
