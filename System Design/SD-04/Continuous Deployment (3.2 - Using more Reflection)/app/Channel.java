package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import util.Ressources;

public class Channel{
	
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		
		String[] classNames = Ressources.getClassNames("app");
		for(String className : classNames)
			try {
				Class<?> cls = Class.forName(className);
				for(Method method : cls.getDeclaredMethods())
					if(method.getParameterCount() == 0
					   && method.getReturnType() == String.class
					   && !Modifier.isStatic(method.getModifiers()))
						try {
							Object object = cls.getConstructor().newInstance();
							messages.add(method.invoke(object).toString());
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
		
		return messages;
	}
}
