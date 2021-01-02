package de.stuttgart.hft.bookapp.communication;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.stuttgart.hft.bookapp.domain.application.Application;
import de.stuttgart.hft.bookapp.domain.model.Order;

@Path("book-app") // Root resource
public class EntryPoint {
	private static Application app = new Application();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome() {
		return "Welcome to the Book-App!";
	}

	@GET
	@Path("authors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authors() {
		return Response.ok(app.getAuthors()).build();
	}
	
	@GET
	@Path("books/author/{authorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response booksByAuthor(@PathParam("authorId") int authorId) {
	return Response.ok(app.getBooksByAuthor(authorId)).build();
	}
	
	@GET
	@Path("books")
	@Produces(MediaType.APPLICATION_JSON)
	public Response booksByAuthorAndGenre(
	@QueryParam("author") int authorId,
	@QueryParam("genre") int genreId) {
	return Response.ok(app.getBooksByAuthorAndGenre(authorId, genreId)).build();}
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmOrder(Order order) {
	app.confirmOrder(order);
	return Response.ok().build();
	}
	
}