package org.craftsmanship.mdtdd.web;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.craftsmanship.mdtdd.app.MdtddApp;
import org.craftsmanship.mdtdd.app.UserDTO;
import org.craftsmanship.mdtdd.core.UserExistsException;
import org.craftsmanship.mdtdd.core.UserNotExistsException;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/user")
public class UserController {

	MdtddApp app = new MdtddApp();

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response register( UserDTO user ) {

		app.registration(user.getName());

		return Response.status(Response.Status.OK).build();
	}

	@POST
	@Path("/{name}")
	@Consumes( MediaType.APPLICATION_JSON )
	public Response follow( @PathParam("name") String userFollowing, UserDTO follower ) {

		app.follow(userFollowing, follower.getName());

		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/{name}")
	@Produces( MediaType.APPLICATION_JSON )
	public Set<UserDTO> following( @PathParam("name") String userFollowing ) {

		return app.following(userFollowing);
	}

	@Provider
	public static class UserNotExceptionMapper implements ExceptionMapper<UserNotExistsException> {

		@Override
		public Response toResponse(UserNotExistsException exception){

		    return Response.status( Response.Status.NOT_FOUND ).
    			entity( exception.getMessage() ).
			    type("text/plain").
                build();
		}
	}

	@Provider
	public static class UserYetExistsExceptionMapper implements ExceptionMapper<UserExistsException> {

		@Override
		public Response toResponse(UserExistsException exception){

		    return Response.status( Response.Status.PRECONDITION_FAILED ).
			    entity( exception.getMessage() ).
			    type("text/plain").
			    build();
		}
	}
}
