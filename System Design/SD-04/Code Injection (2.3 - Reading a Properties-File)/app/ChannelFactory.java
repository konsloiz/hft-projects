package app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ChannelFactory {
	
	

	public Channel newChannel() {
		
		
		String msgrClassName = getProperty("message.properties", "msgr");
		System.out.println("found: " + msgrClassName);
		return new Channel();
		
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
