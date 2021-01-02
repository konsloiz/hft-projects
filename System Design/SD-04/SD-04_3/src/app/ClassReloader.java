package app;

import java.net.URL;
import java.net.URLClassLoader;

import util.Ressources;

public class ClassReloader extends URLClassLoader {
	public ClassReloader() {
		super(classPath(), null);
	}

	private static URL[] classPath() {
		URL url = Ressources.getBaseDir();
		URL[] urls = { url };
		return urls;
	}
}