package app;

import pipeline.kernel.Pipeline;
import pipeline.pipes.OutputDrain;
import pipeline.pipes.TextFileSource;

public class Sample {
	
	public static String keep(String s) {
		return s;
	}

	public static void main(String[] args) {	
		Pipeline pl = Pipeline.source(new TextFileSource("test.txt"))
				.map(s -> keep(s))
				.drain(new OutputDrain<String>());		
		pl.start();		
	}
}
