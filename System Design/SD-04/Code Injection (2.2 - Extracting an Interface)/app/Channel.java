package app;

import java.util.ArrayList;
import java.util.List;

public class Channel{
	
	private Messanger msgr = new ConcreteMessenger1();
	
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		
	
		messages.add(msgr.message());
		
		return messages;
	}
}
