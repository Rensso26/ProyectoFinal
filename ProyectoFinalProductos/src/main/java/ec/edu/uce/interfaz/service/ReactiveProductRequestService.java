package ec.edu.uce.interfaz.service;


import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
public class ReactiveProductRequestService {

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    private ToyService toyService;

    /**
     * Crea una solicitud de producto de manera reactiva.
     *
     * @param user El usuario que realiza la solicitud.
     * @param toy  El juguete solicitado.
     * @return Un Mono que emite la solicitud de producto creada.
     */
    public Mono<ProductRequest> createRequest(User user, Toy toy) {
        return Mono.fromCallable(() -> productRequestService.createRequest(user, toy))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Procesa una solicitud de producto de manera reactiva.
     *
     * @param requestId El ID de la solicitud a procesar.
     * @return Un Mono que completa cuando la solicitud ha sido procesada.
     */
    public Mono<Void> processRequest(Long requestId) {
        return Mono.fromRunnable(() -> {
            ProductRequest request = productRequestService.findById(requestId);
            if (request != null && request.getToy() != null) {
                Toy toy = request.getToy();
                double totalTime = toy.getCreationForm().getTimeEnsable() +
                        toy.getCreationForm().getTimePinter() +
                        toy.getCreationForm().getTimePackaging();
                try {
                    // Simula el tiempo de fabricación
                    Thread.sleep((long) (totalTime * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                request.setStatus("COMPLETED");
                productRequestService.update(request);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    /**
     * Obtiene todas las solicitudes pendientes de manera reactiva.
     *
     * @return Un Flux que emite todas las solicitudes pendientes.
     */
    public Flux<ProductRequest> getPendingRequests() {
        return Flux.fromIterable(productRequestService.getPendingRequests());
    }
}
