package app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ChannelFactory {
	
	public Channel newChannel() {
			
		String msgrClassName = getProperty("message.properties", "msgr");
		System.out.println("found: " + msgrClassName);
		
		Messenger messenger = newInstance(msgrClassName);
		System.out.println("created: " + messenger);
		
		return new Channel();	
	}
	
	private Messenger newInstance(String msgrClassName) {
		
		try {
			Class<? extends Messenger> cls = Class.forName(msgrClassName).asSubclass(Messenger.class);
			Messenger messenger = cls.getConstructor().newInstance();
			return messenger;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String getProperty(String file, String key) {
		
		Properties properties = new Properties();
		try (InputStream stream = new BufferedInputStream(new FileInputStream(file))){
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String propString = properties.getProperty(key);
		
		return propString;
	}

}
