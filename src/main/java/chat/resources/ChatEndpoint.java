/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
/**
 * REST Web Service
 *
 * @author Tarllark
 */
@Path("chat")
public class ChatEndpoint
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ChatEndpoint
     */
    public ChatEndpoint()
    {
    }
    
    ChatFacade cf = new ChatFacade();
    JSONParser parser = new JSONParser();
    
    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatHistoryByID(@PathParam("id") Integer id) {
        return Response.ok().entity(cf.getHistory(id)).build();
    }

    @GET
    @Path("/chat/{id}/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatSessionByID(@PathParam("id") Integer id, @PathParam("id2") Integer id2) {
        return Response.ok().entity(cf.getChatSession(id, id2)).build();
    }
    
    @GET
    @Path("/{id}/newest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewMsgByID(@PathParam("id") Integer id) {
        return Response.ok().entity(cf.getNewMessage(id)).build();
    }
    
    @POST
    @Path("/{id}/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCreateMsg(String json, @PathParam("id") Integer id) throws ParseException {
        return Response.ok().entity(cf.createMSG((JSONObject) parser.parse(json))).build();
    }
}
