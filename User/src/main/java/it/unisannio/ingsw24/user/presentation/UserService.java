package it.unisannio.ingsw24.user.presentation;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.logic.UserLogic;
import it.unisannio.ingsw24.user.logic.UserLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * This class is the RESTful service for the user entity.
 * It provides the following functionalities:
 * - Create a new user
 * - Get a user by username
 * - Delete a user by username
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserService {

    private UserLogic logic;

    /**
     * Constructor of the class.
     */
    public UserService(){
        logic = new UserLogicImpl();
    }

    /**
     * This method creates a new user. It receives a user object and returns the response of the operation.
     * @param user The user to be created.
     * @return The response of the operation.
     */
    @POST
    public Response createUser(MyUser user){

        String username = logic.createUser(user);

        if (username != null){
            URI uri = UriBuilder.fromPath("/users/{username}").build(username);
            return Response.created(uri).build();
        }
        else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * This method gets a user by username. It receives the username and returns the response of the operation.
     * @param username The username of the user to be retrieved.
     * @return The response of the operation.
     */
    @GET
    @Path("{username}")
    public Response getUser(@PathParam("username") String username){
        MyUser user = logic.getUser(username);
        return Response.ok(user).build();
    }

    /**
     * This method deletes a user by username. It receives the username and returns the response of the operation.
     * @param username The username of the user to be deleted.
     * @return The response of the operation.
     */
    @DELETE
    @Path("{username}")
    public Response deleteUser(@PathParam("username") String username){
        boolean b = logic.deleteUser(username);
        if (b) return Response.noContent().build();
        return Response.serverError().build();
    }
}
