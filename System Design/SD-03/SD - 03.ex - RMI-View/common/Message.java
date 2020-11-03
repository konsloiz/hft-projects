package common;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String contentType;
	private String messageText;

	public Message(String contentType, String messageText) {
		super();
		this.contentType = contentType;
		this.messageText = messageText;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "Message [contentType=" + contentType + ", messageText=" + messageText + "]";
	}
}