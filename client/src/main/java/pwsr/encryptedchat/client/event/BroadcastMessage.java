package pwsr.encryptedchat.client.event;

import java.io.Serializable;

/**
 * @author Adrian Michalski
 */
public class BroadcastMessage implements Serializable {

    private String userName;
    private String text;
    private Object sender;

    public BroadcastMessage() {
    }

    public BroadcastMessage(String userName, String text, Object sender) {
        this.userName = userName;
        this.text = text;
        this.sender = sender;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public Object getSender() {
        return sender;
    }

}
