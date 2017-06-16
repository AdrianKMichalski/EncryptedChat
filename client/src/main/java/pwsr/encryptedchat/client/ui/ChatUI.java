package pwsr.encryptedchat.client.ui;

import com.vaadin.annotations.Push;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import pwsr.encryptedchat.client.ReceiverClient;
import pwsr.encryptedchat.client.event.Broadcaster;

import javax.inject.Inject;

/**
 * @author Adrian Michalski
 */
@CDIUI("")
@Push
public class ChatUI extends UI {

    @Inject
    Broadcaster broadcaster;

    @Inject
    ChatBox chatBox;

    private String userName;
    private String receiverAddress;
    private String cryptoServiceAddress;

    @Override
    protected void init(VaadinRequest request) {
        broadcaster.register(this);

        FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.addStyleName("outlined");
        layout.setSizeFull();

        Label label = new Label("<h1>Czat z szyfrowaniem</h1>", ContentMode.HTML);
        layout.addComponent(label);

        TextField userNameField = new TextField("Twój nick");
        userNameField.addValueChangeListener(e -> userName = e.getValue());
        userNameField.setIcon(FontAwesome.EYE);
        userNameField.setRequiredIndicatorVisible(true);
        userNameField.setWidth("100%");

        layout.addComponent(userNameField);
        TextField receiverAddressField = new TextField("Adres rozmówcy");
        receiverAddressField.addValueChangeListener(e -> receiverAddress = e.getValue());
        receiverAddressField.setIcon(FontAwesome.USER);
        receiverAddressField.setRequiredIndicatorVisible(true);
        receiverAddressField.setWidth("100%");
        layout.addComponent(receiverAddressField);

        TextField encodeServiceAddressField = new TextField("Adres usługi szyfrującej");
        encodeServiceAddressField.addValueChangeListener(e -> cryptoServiceAddress = e.getValue());
        encodeServiceAddressField.setIcon(FontAwesome.EXPEDITEDSSL);
        layout.addComponent(encodeServiceAddressField);
        encodeServiceAddressField.setWidth("100%");

        Button startChatButton = new Button("Uruchom czat");
        startChatButton.setWidth("100%");
        layout.addComponent(startChatButton);
        setContent(layout);

        startChatButton.addClickListener(clickEvent -> addWindow(createChatWindow()));
    }

    public Window createChatWindow() {
        Window subWindow = new Window("Czat z " + receiverAddress);
        subWindow.setModal(true);
        subWindow.setWidth("75%");
        subWindow.setHeight("75%");
        subWindow.center();
        subWindow.setContent(chatBox);
        chatBox.setUserName(userName);
        chatBox.setReceiverAddress(receiverAddress);
        chatBox.setCryptoServiceAddress(cryptoServiceAddress);
        chatBox.setSizeFull();
        return subWindow;
    }

    @Override
    public void detach() {
        broadcaster.unregister(this);
        super.detach();
    }

}
