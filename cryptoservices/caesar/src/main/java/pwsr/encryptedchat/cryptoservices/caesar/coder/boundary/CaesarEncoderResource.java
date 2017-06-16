package pwsr.encryptedchat.cryptoservices.caesar.coder.boundary;

import pwsr.encryptedchat.cryptoservices.caesar.CaesarCoderApp;
import pwsr.encryptedchat.cryptoservices.caesar.coder.control.CaesarCoderService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Adrian Michalski
 */
@Path("encoder")
public class CaesarEncoderResource {

    @Inject
    CaesarCoderService coderService;

    @GET
    public String decode(@QueryParam("message") String pMessage) {
        return coderService.shiftCharacters(pMessage, CaesarCoderApp.DEFAULT_CAESAR_SHIFT);
    }

}
