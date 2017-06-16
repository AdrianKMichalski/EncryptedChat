package pwsr.encryptedchat.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Adrian Michalski
 */
@ApplicationScoped
public class CryptoServiceClient {

    private Client client;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();
    }

    public String encodeMessage(String pCryptoServiceUri, String pMessage) {
        return client.target(pCryptoServiceUri)
                .path("encoder")
                .queryParam("message", pMessage)
                .request()
                .get(String.class);
    }

    public String decodeMessage(String pCryptoServiceUri, String pMessage) {
        return client.target(pCryptoServiceUri)
                .path("decoder")
                .queryParam("message", pMessage)
                .request()
                .get(String.class);
    }

}
