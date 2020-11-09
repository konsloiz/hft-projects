package app;

import java.util.ArrayList;
import java.util.List;

public class Channel{
	
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		
		Messenger msgr = new Messenger();
		messages.add(msgr.message());
		
		return messages;
	}
}
