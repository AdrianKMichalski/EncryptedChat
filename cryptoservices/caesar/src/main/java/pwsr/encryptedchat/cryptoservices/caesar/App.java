package pwsr.encryptedchat.cryptoservices.caesar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Adrian Michalski
 */
@Path("/hello")
public class App {

    @GET
    public Response getName() {
        return Response.ok("Hello world!").build();
    }

}
