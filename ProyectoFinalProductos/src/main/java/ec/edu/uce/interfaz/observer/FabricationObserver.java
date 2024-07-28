package ec.edu.uce.interfaz.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class FabricationObserver implements Observer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void update(String message) {
        messagingTemplate.convertAndSend("/topic/fabrication", message);
    }
}
