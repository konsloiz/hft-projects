package de.stuttgart.hft.bookapp.communication;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class BookServer {
	public static <T> void forceInit(Class<T> cls) {
		try {
			Class.forName(cls.getName(), true, cls.getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e); // Can't happen
		}
	}

	public void startServer(int port, String contextPath) {
		forceInit(EntryPoint.class);
		try {
			Server server = new Server(port);
			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath(contextPath);
			server.setHandler(context);
			ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
			jerseyServlet.setInitOrder(0);
// Tell the Jersey Servlet which REST service/class to load.
			jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
					EntryPoint.class.getCanonicalName());
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}