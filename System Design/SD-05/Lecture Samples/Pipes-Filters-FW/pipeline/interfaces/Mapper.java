package pipeline.interfaces;

@FunctionalInterface
public interface Mapper<S, T> {
	
	T map(S s);
	
}
