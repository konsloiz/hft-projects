package util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Ressources {
	
	// Use this class as reference
	private static final Class<?> THIS = Ressources.class;

	private static final String CLASS_EXT = ".class";
	private static final char PACKAGE_SAPARATOR = '.';
	private static final int CLASS_EXT_LENGTH = CLASS_EXT.length();

	public static URL getBaseDir() {
		try { 
			URL url = THIS.getClassLoader().getResource(".");
			
			String protocol	= url.getProtocol();
			String host		= url.getHost();
			String path		= url.getPath();
			
			path = URLDecoder.decode(path, "UTF-8");				
			url = new URL(protocol, host, path);
		    return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		throw new FileSystemNotFoundException();
	}

	public static String[] getClassNames(){
		return getClassNames("", false);
	}

	public static String[] getClassNames(String packageName){
		return getClassNames(packageName, false);
	}

	public static String[] getClassNames(boolean includingSubpackages){
		return getClassNames("", includingSubpackages);
	}
	
	public static File getClassFile(Class<?> cls) {
		return getClassFile(cls.getName());
	}

	public static File getClassFile(String name) {
		URL url = getBaseDir();
		String path = url.getPath();
		path = path+ File.separatorChar + name.replace(PACKAGE_SAPARATOR, File.separatorChar) + CLASS_EXT;
		File file = new File(path);		
		return file;
	}

	public static String[] getClassNames(String packageName, boolean includingSubpackages){
		URL url = getBaseDir();
		String path = url.getPath();
		File dir = new File(path);
		int prefixLength = dir.getPath().length() + 1;
		
		if(!(packageName.isEmpty() || packageName.equals("."))) {
			packageName = packageName.replace(PACKAGE_SAPARATOR, File.separatorChar);
			String dirName = dir.getPath() + File.separatorChar + packageName;
			dir = new File(dirName);
		}
		
		List<String> classNames = getClassNames(dir, prefixLength, new ArrayList<>(), includingSubpackages);
		return classNames.toArray(new String[classNames.size()]);		
	}
	
	private static List<String> getClassNames(File dir, int prefixLength, List<String> classNames, boolean includingSubpackages){
		Stream.of(dir.listFiles((file, name) -> name.endsWith(CLASS_EXT) && !name.contains("$")))
			.map(file -> file.getPath()).map(path -> path.substring(prefixLength, path.length()))
			.map(name -> name.substring(0, name.length() - CLASS_EXT_LENGTH))
			.map(name -> name.replace(File.separatorChar, PACKAGE_SAPARATOR))
			.forEach(name -> classNames.add(name));
		if(includingSubpackages)
			Stream.of(dir.listFiles(file -> file.isDirectory()))
				.forEach(file -> getClassNames(file, prefixLength, classNames, includingSubpackages));
		return classNames;
	}
}
