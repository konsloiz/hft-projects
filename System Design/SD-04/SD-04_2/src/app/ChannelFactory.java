package app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChannelFactory {

	public Channel newChannel() {

		String msgrClassName = getProperty("message.properties", "msgr");
//		System.out.println("found: " + msgrClassName);

		Messenger messenger = newInstance(msgrClassName);
//		System.out.println("created: " + messenger);

		Channel channel = new Channel();
		List<Field> fields = getFields(channel, MessengerContext.class);
//		System.out.println("fields: " + fields);

		for (Field field : fields)
			try {
				field.set(channel, messenger);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		return channel;
	}

	private List<Field> getFields(Object object, Class<? extends Annotation> annotation) {
		List<Field> fields = new ArrayList<>();
		for (Field field : object.getClass().getDeclaredFields())
			if (field.isAnnotationPresent(annotation)) {
				field.setAccessible(true);
				fields.add(field);
			}
		return fields;
	}

	private Messenger newInstance(String msgrClassName) {
		try {
			Class<? extends Messenger> cls = Class.forName(msgrClassName).asSubclass(Messenger.class);
			Messenger messenger = cls.getConstructor().newInstance();
			return messenger;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getProperty(String file, String key) {
		Properties properties = new Properties();
		try (InputStream stream = new BufferedInputStream(new FileInputStream(file))) {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String propString = properties.getProperty(key);
		return propString;
	}
}
