/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import entity.UserDTO;
import entity.UserFacade;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("User")
public class UserEndpoint
{

    @Context
    private UriInfo context;

    public UserEndpoint()
    {
    }
    UserFacade uf = new UserFacade();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByID(@PathParam("id") Integer id) {
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id))).build();
    }
    @GET
    @Path("/{id}/desc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDescByID(@PathParam("id") Integer id) {
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id).getDesc())).build();
    }
    
    @GET
    @Path("/{id}/interest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInterestByID(@PathParam("id") Integer id) {
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id).getInterests())).build();
    }
    
    @GET
    @Path("/allasmap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok().entity(gson.toJson(uf.getUsers())).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(String content) {
        User newUser = gson.fromJson(content, User.class);
        System.out.println("New user: " + newUser);
        uf.addUser(newUser);
        return Response.ok().entity(gson.toJson(newUser)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserDesc( String content, @PathParam("id") int id)  {
        UserDTO newUser = gson.fromJson(content, UserDTO.class);
        UserDTO savedUser = uf.getUserDTO(id);
        if(newUser.getDesc()!=null)
            savedUser.setDesc(newUser.getDesc());
        return Response.ok().entity(gson.toJson(savedUser)).build();
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id")int id) {
        User us = uf.deleteUser(id);
        return Response.ok().entity(gson.toJson(us)).build();
    }
}