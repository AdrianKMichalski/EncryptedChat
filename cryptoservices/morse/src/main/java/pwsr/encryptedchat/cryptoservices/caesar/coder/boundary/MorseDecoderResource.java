package pwsr.encryptedchat.cryptoservices.caesar.coder.boundary;

import pwsr.encryptedchat.cryptoservices.caesar.coder.control.MorseCoderService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Adrian Michalski
 */
@Path("decoder")
public class MorseDecoderResource {

    @Inject
    MorseCoderService coderService;

    @GET
    public String decode(@QueryParam("message") String pMessage) {
        return coderService.decode(pMessage);
    }

}
