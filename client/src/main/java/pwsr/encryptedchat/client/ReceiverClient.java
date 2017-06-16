package pwsr.encryptedchat.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * @author Adrian Michalski
 */
@RequestScoped
public class ReceiverClient {

    private Client client;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();
    }

    public Response sendMessage(String pReceiverUri, String pUserName, String pMessage) {
        return client.target(pReceiverUri)
                .queryParam("user", pUserName)
                .queryParam("message", pMessage)
                .request()
                .get();
    }

//    public ForecastResponse getForecast(String place) {
//        return target.queryParam("q", place)
//                .request(MediaType.APPLICATION_JSON)
//                .get(ForecastResponse.class);
//    }
}
