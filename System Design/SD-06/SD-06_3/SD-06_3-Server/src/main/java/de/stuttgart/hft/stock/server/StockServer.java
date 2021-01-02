package de.stuttgart.hft.stock.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class StockServer {
	

	public void start(int port, String contextPath, String serviceClassName) { 
		try { 
			Server server = new Server(port); 
		 
			ServletContextHandler context = 
				new ServletContextHandler(ServletContextHandler.SESSIONS); 
			context.setContextPath(contextPath); 
			server.setHandler(context); 
		 
			ServletHolder jerseyServlet = context.addServlet(
					org.glassfish.jersey.servlet.ServletContainer.class, "/*"); 
			jerseyServlet.setInitOrder(0); 
		 
			// Tell the Jersey Servlet which REST service/class to load. 
			jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", serviceClassName); 
		 
			server.start(); 
			server.join(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
}
