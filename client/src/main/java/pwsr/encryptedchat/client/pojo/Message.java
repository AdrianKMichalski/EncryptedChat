package pwsr.encryptedchat.client.pojo;

/**
 * @author Adrian Michalski
 */
public class Message {

    private String senderName;
    private String value;

    public Message(String pSenderName, String pValue) {
        senderName = pSenderName;
        value = pValue;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getValue() {
        return value;
    }

}
