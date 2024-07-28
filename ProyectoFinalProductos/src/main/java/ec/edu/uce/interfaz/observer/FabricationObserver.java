package ec.edu.uce.interfaz.observer;

import org.springframework.stereotype.Component;

@Component
public class FabricationObserver implements Observer {

    @Override
    public void update(String message) {
        // Manejar el mensaje, por ejemplo, enviarlo al cliente a través de WebSocket o registrarlo
        System.out.println(message);
    }
}
