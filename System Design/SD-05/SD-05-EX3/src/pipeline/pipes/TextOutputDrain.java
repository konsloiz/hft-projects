package pipeline.pipes;

import pipeline.interfaces.Pipe;

public class TextOutputDrain implements Pipe<String>{

	@Override
	public void put(String t) {
		if(t != null)
			System.out.println(t);
		else
			System.out.println("<done>");
	}
}
