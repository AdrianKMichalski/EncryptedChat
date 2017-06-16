package pwsr.encryptedchat.client.event;

import com.vaadin.ui.UI;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Adrian Michalski
 */
@ApplicationScoped
public class Broadcaster implements Serializable {

    @Inject
    @SentByBroadcaster
    private javax.enterprise.event.Event<BroadcastMessage> messageEvent;

    private Collection<UI> uis = new HashSet<>();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public synchronized void register(UI listener) {
        uis.add(listener);
    }

    public synchronized void unregister(UI listener) {
        uis.remove(listener);
    }

    @SuppressWarnings("unused")
    private synchronized void observeMessage(@Observes @SentByClient final BroadcastMessage message) {
        for (final UI ui : uis) {
            executorService.execute(() -> ui.access(() -> messageEvent.fire(message)));
        }
    }

}

