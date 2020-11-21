package pipeline.pipes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import pipeline.interfaces.Pipe;

public class TextFileSource implements Pipe<String> {
	
	private enum State{ INITIAL, READING, DONE, ERROR };

	private State state;
	private String filename;
	private BufferedReader buffer;

	public TextFileSource(String filename) {
		this.filename = filename;
		this.state = State.INITIAL;
	}

	@Override
	public boolean consumes() {
		return false;
	}

	@Override
	public boolean isServing() {
		return state == State.READING || state == State.INITIAL ;
	}

	@Override
	public String get() {
		String line = null;
		switch(state) {
		case DONE:
		case ERROR: break;
		case INITIAL: line = initAndRead(); break;
		case READING: line = read(); break;
		}
		return line;
	}
	
	@Override
	public List<String> get(int n) {
		List<String> lines = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			String line = get();
			if(line != null) lines.add(line);
		}
		return lines;
	}

	private String initAndRead() {
		try {
			Reader reader = new FileReader(filename);
			buffer = new BufferedReader(reader);
			state = State.READING;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			state = State.ERROR;
			return null;
		}		
		return read();
	}

	private String read() {
		String line = null;
		try {
			line = buffer.readLine();
			if(line == null) { // done
				buffer.close();
				buffer = null;
				state = State.DONE;
			}			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				buffer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			buffer = null;
			state = State.ERROR;
		}
		return line;
	}
}
