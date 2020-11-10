package app;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Ressources;

public class Channel{
	
	private Map<String, Long> loaded = new HashMap<>();
	
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		
		String[] classNames = Ressources.getClassNames("app");
		for(String className : classNames)
			try {
				
				Class<?> cls;
				Class<?> strCls;
				Class<? extends Annotation> annCls;
				Long modified = loaded.get(className);
				long lastModified = Ressources.getClassFile(className).lastModified();
				
				if(modified == null) { // not loaded so far
					cls = Class.forName(className);
					strCls = String.class;
					annCls = Displayable.class;
					loaded.put(className, lastModified);
				} else if(modified < lastModified) { // class has changed
					ClassLoader loader = new ClassReloader();
					strCls = Class.forName(String.class.getName(), true, loader);
					annCls = Class.forName(Displayable.class.getName(), true, loader).asSubclass(Annotation.class);
					cls = Class.forName(className, true, loader); 
					loaded.put(className, lastModified);
				}else
					continue;
				
				for(Method method : cls.getMethods())
					if(method.getParameterCount() == 0
					   && method.getReturnType() == strCls
					   && !Modifier.isStatic(method.getModifiers())
					   && method.isAnnotationPresent(annCls))
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
