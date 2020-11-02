package de.stuttgart.hft.bookapp.server.swagger;


import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

import java.net.URISyntaxException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import de.stuttgart.hft.bookapp.server.communication.EntryPoint;

// Open Swagger at: http://localhost:8080/api/docs

public class BookServerWithSwagger {
	
	private static final int SERVER_PORT = 8080;
	private static Class<?> CLASS = EntryPoint.class;
	
	// see: https://stackoverflow.com/questions/3560103/how-to-force-a-class-to-be-initialised
	
	// Forces the initialization of the class pertaining to the 
	// specified <tt>Class</tt> object. This method does nothing if 
	// the class is already initialized prior to invocation.

	public static <T> void forceInit(Class<T> cls) {
	    try {
	        Class.forName(cls.getName(), true, cls.getClassLoader());
	    } catch (ClassNotFoundException e) {
	        throw new AssertionError(e);  // Can't happen
	    }
	} 


	private void buildSwaggerBean() {
		// This configures Swagger
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setResourcePackage(CLASS.getPackage().getName());
		beanConfig.setScan(true);
		beanConfig.setBasePath("/api"); // "/"
		beanConfig.setDescription("REST-API to demonstrate Swagger with Jersey2 in an "
				+ "embedded Jetty instance, with no web.xml or Spring MVC.");
		beanConfig.setTitle("API");
	}

	private ContextHandler buildApplicationContext() {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages(CLASS.getPackage().getName(), ApiListingResource.class.getPackage().getName());
		ServletContainer servletContainer = new ServletContainer(resourceConfig);
		ServletHolder servletHolder = new ServletHolder(servletContainer);
		ServletContextHandler applicationContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		applicationContext.setContextPath("/");
		applicationContext.addServlet(servletHolder, "/api/*"); // "/*"

		return applicationContext;
	}

	// This starts the Swagger UI at http://localhost:PORT/api/docs
	private ContextHandler buildSwaggerUI() throws URISyntaxException{
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase(this.getClass().getClassLoader().getResource("swaggerui").toURI().toString());
		ContextHandler swaggerUIContext = new ContextHandler();
		swaggerUIContext.setContextPath("/api/docs/"); // "/docs"
		swaggerUIContext.setHandler(resourceHandler);

		return swaggerUIContext;
	}

	public void startServer(){
		forceInit(EntryPoint.class);
		
		try {
			
			Resource.setDefaultUseCaches(false); // Workaround for resources from JAR files
			buildSwaggerBean();

			HandlerList handlers = new HandlerList();
			handlers.addHandler(buildSwaggerUI()); // Handler for Swagger UI, static handler.
			handlers.addHandler(buildApplicationContext()); // Handler for App and Swagger

			// Start server
			Server server = new Server(SERVER_PORT);
			server.setHandler(handlers);
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Open Swagger at: http://localhost:8080/api/docs;
//      System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
        BookServerWithSwagger server = new BookServerWithSwagger();
		server.startServer();
	}
}
