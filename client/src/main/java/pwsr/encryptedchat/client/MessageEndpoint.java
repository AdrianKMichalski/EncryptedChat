package pwsr.encryptedchat.client;

import pwsr.encryptedchat.client.event.BroadcastMessage;
import pwsr.encryptedchat.client.event.SentByClient;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Adrian Michalski
 */
@WebServlet("/api")
public class MessageEndpoint extends HttpServlet {

    @Inject
    @SentByClient
    private javax.enterprise.event.Event<BroadcastMessage> messageEvent;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        messageEvent.fire(new BroadcastMessage(request.getParameter("user"), request.getParameter("message"), null));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}