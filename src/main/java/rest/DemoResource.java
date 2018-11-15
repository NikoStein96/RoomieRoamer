package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import Resources.*;
import java.sql.SQLException;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource
{

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from USER: " + user + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from ADMIN" + user + "\"";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    //@RolesAllowed({"user","admin"})
    public String getAll()
    {
        String data = new externalData().getAll();
        return "\"Here is some Star Wars data! And lots and lots of it!" + data + "\"";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dummy/{offset}")
    public String getDummyData(@PathParam("offset") Integer offset) throws SQLException, ClassNotFoundException
    {
        String data = new Resources.DBAccess.dbData().getData(offset);
        return data;
    }
    
}
