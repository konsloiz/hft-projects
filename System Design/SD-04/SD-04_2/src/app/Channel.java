package app;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	@MessengerContext
	private Messenger msgr;

	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();

		messages.add(msgr.message());

		return messages;
	}
}
