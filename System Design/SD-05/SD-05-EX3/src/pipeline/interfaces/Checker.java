package pipeline.interfaces;

@FunctionalInterface
public interface Checker <S> {
	
	boolean check(S s);
	
}
