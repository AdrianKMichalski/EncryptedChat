package pwsr.encryptedchat.cryptoservices.bacon.coder.boundary;

import pwsr.encryptedchat.cryptoservices.bacon.coder.control.BaconCoderService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Adrian Michalski
 */
@Path("decoder")
public class BaconDecoderResource {

    @Inject
    BaconCoderService coderService;

    @GET
    public String decode(@QueryParam("message") String pMessage) {
        return coderService.decode(pMessage);
    }

}
