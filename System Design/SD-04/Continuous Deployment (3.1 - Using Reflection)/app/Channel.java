package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Channel{
	
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		
		Messenger msgr = new Messenger();
		for (Method method : msgr. getClass().getDeclaredMethods())
			if ((method.getParameterCount() == 0) && method.getReturnType() == String.class)
				try {
					messages.add(method.invoke(msgr).toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
		return messages;
	}
}
