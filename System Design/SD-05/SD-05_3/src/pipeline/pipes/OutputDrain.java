package pipeline.pipes;

import pipeline.interfaces.Pipe;

public class OutputDrain<T> implements Pipe<T>{

	@Override
	public void put(T t) {
		if(t != null)
			System.out.println(t.toString());
		else
			System.out.println("<done>");
	}
}
