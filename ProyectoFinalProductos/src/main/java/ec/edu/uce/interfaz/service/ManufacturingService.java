package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.repository.ToyRepository;
import ec.edu.uce.interfaz.state.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class ManufacturingService {

    @Autowired
    private ToyRepository toyRepository;

    @Async
    public CompletableFuture<String> fabricateToyAsync(Long toyId) {
        return CompletableFuture.supplyAsync(() -> {
            Toy toy = toyRepository.findById(Math.toIntExact(toyId)).orElseThrow(() -> new RuntimeException("Toy not found"));
            double totalTime = toy.getCreationForm().getTimeEnsable() + toy.getCreationForm().getTimePinter() +
                    toy.getCreationForm().getTimePackaging();
            try {
                Thread.sleep((long) (totalTime * 1000L)); // Emulate manufacturing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return toy.getName() + " fabricated in " + totalTime + " seconds";
        });
    }

    public Mono<String> fabricateToy(Long toyId) {
        return Mono.fromFuture(fabricateToyAsync(toyId));
    }
}
