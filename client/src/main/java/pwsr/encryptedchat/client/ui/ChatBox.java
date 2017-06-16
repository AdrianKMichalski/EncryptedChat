package pwsr.encryptedchat.client.ui;

import com.vaadin.cdi.UIScoped;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import pwsr.encryptedchat.client.ReceiverClient;
import pwsr.encryptedchat.client.event.BroadcastMessage;
import pwsr.encryptedchat.client.event.SentByBroadcaster;
import pwsr.encryptedchat.client.event.SentByClient;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Random;

/**
 * @author Adrian Michalski
 */
@UIScoped
public class ChatBox extends CustomComponent {

    @Inject
    ReceiverClient receiverClient;

    @Inject
    @SentByClient
    private javax.enterprise.event.Event<BroadcastMessage> messageEvent;

    private String localUserName;
    private String receiverAddress;

    private TextArea textArea;

    public ChatBox() {
        GridLayout grid = new GridLayout(2, 2);
        grid.setSpacing(false);
        grid.setColumnExpandRatio(0, 1);
        grid.setRowExpandRatio(0, 1);

        textArea = new TextArea();
        textArea.setSizeFull();
        textArea.setReadOnly(true);
        grid.addComponent(textArea, 0, 0, 1, 0);
        grid.setComponentAlignment(textArea, Alignment.TOP_CENTER);

        localUserName = "user-" + new Random().nextInt(100);

        TextField messageTextField = new TextField();
        messageTextField.setSizeFull();
        grid.addComponent(messageTextField, 0, 1);
        grid.setComponentAlignment(messageTextField, Alignment.BOTTOM_LEFT);

        Button sendButton = new Button("Wyślij", e -> {
            String message = messageTextField.getValue();

            // TODO: encode message

            broadcast(message);
            addMessage(localUserName, message);
            messageTextField.setValue("");
            messageTextField.focus();
        });
        sendButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        grid.addComponent(sendButton, 1, 1);
        grid.setComponentAlignment(sendButton, Alignment.BOTTOM_RIGHT);
        grid.setSizeFull();

        messageTextField.focus();

        setCompositionRoot(grid);
    }

    public void setUserName(String pNewUserName) {
        localUserName = pNewUserName;
    }

    public void setReceiverAddress(String pReceiverAddress) {
        receiverAddress = pReceiverAddress;
    }

    private void broadcast(String pMessageText) {
        messageEvent.fire(new BroadcastMessage(localUserName, pMessageText, this));

        new Notification("Informacja",
                "Rozpoczęcie wysyłania",
                Notification.Type.TRAY_NOTIFICATION, true)
                .show(Page.getCurrent());

        Response response = receiverClient.sendMessage(receiverAddress, localUserName, pMessageText);
        if (Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
            new Notification("Informacja",
                    "Wysyłanie powiodło się",
                    Notification.Type.TRAY_NOTIFICATION, true)
                    .show(Page.getCurrent());
        } else {
            new Notification("Informacja",
                    "DUPA DUPA",
                    Notification.Type.TRAY_NOTIFICATION, true)
                    .show(Page.getCurrent());

        }
    }

    @SuppressWarnings("unused")
    private void observeMessage(@Observes @SentByBroadcaster BroadcastMessage event) {
        if (event.getSender() != this) {
            // TODO: decode message
            addMessage(event.getUserName(), event.getText());
        }
    }

    private void addMessage(String pUserName, String pMessage) {
        textArea.setReadOnly(false);
        textArea.setValue(textArea.getValue() + "\n" + pUserName + "> " + pMessage);
        textArea.setCursorPosition(textArea.getValue().length());
        textArea.setReadOnly(true);
    }
}
