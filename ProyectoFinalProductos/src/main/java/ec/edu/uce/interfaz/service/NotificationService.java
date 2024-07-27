package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.ProductRequestObservable;
import ec.edu.uce.interfaz.Interfaces.ProductRequestObserver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NotificationService implements ProductRequestObserver {

    @Override
    public void update(ProductRequest request) {
        // Lógica para notificar al usuario del cambio de estado
    }

    public Mono<Void> notifyChange(ProductRequest request) {
        return Mono.fromRunnable(() -> {
            // Lógica para notificar al usuario del cambio de estado de manera reactiva
        });
    }
}
