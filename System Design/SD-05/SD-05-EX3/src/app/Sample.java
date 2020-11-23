package app;

import pipeline.kernel.Pipeline;
import pipeline.pipes.OutputDrain;
import pipeline.pipes.TextFileSource;

public class Sample {
	
	public static String keep(String s) {
		return s;
	}
	
	public static String toUpperCase(String s) {
		return s.toUpperCase();
	}
	
	public static String removeVowels(String s) {
		return s.replaceAll ( "A|E|I|O", "");
	}
	

	public static void main(String[] args) {	
		Pipeline pl = Pipeline.source(new TextFileSource("test.txt"))
				.map(s -> toUpperCase(s))
				.map(s -> removeVowels(s))
				.drain(new OutputDrain<String>());		
		pl.start();		
	}
}
