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



    public Mono<ProductRequest> createRequest(User user, Toy toy) {
        return Mono.fromCallable(() -> productRequestService.createRequest(user, toy))
                .subscribeOn(Schedulers.boundedElastic());
    }


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


    public Flux<ProductRequest> getPendingRequests() {
        return Flux.fromIterable(productRequestService.getPendingRequests());
    }
}
