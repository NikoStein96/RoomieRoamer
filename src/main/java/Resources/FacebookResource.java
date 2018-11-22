package Resources;

import entity.User;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("facebook")
public class FacebookResource {

    @Context
    private UriInfo context;

    public FacebookResource() {
    }

    @GET
    @Path("/getUserData/{userId}/{accessToken}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("userId") String id, @PathParam("accessToken") String access) throws MalformedURLException, IOException {
        URL url = new URL(  "https://graph.facebook.com/v3.2/" + id + "?fields=id,name,picture&access_token=" + access);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
            System.out.println(jsonStr);
        }
        scan.close();
        return jsonStr;
    }

    /**
     * PUT method for updating or creating an instance of FacebookResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
