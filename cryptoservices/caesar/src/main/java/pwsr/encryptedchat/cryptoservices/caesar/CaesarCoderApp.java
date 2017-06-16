package pwsr.encryptedchat.cryptoservices.caesar;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * @author Adrian Michalski
 */
@ApplicationPath("/")
public class CaesarCoderApp extends Application {

    public static final int DEFAULT_CAESAR_SHIFT = 13;

}
