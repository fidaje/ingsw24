package it.unisannio.ingsw24.user.presentation;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.logic.UserLogic;
import it.unisannio.ingsw24.user.logic.UserLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserService {

    private UserLogic logic;

    public UserService(){
        logic = new UserLogicImpl();
    }

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


    @GET
    @Path("{username}")
    public Response getUser(@PathParam("username") String username){
        MyUser user = logic.getUser(username);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteUser(@PathParam("username") String username){
        boolean b = logic.deleteUser(username);
        if (b) return Response.noContent().build();
        return Response.serverError().build();
    }
}
