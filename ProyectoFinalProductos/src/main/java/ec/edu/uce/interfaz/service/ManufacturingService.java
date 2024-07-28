package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ManufacturingService {

    @Autowired
    private ToyRepository toyRepository;

    public Mono<String> fabricateToy(Long toyId) {
        return Mono.fromCallable(() -> {
            Toy toy = toyRepository.findById(Math.toIntExact(toyId))
                    .orElseThrow(() -> new RuntimeException("Toy not found"));

            double totalFabricationTime = (double) (toy.getCreationForm().getTimeEnsable()
                                + toy.getCreationForm().getTimePinter()
                                + toy.getCreationForm().getTimePackaging());

            try {
                Thread.sleep((long) (totalFabricationTime * 1000L));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Fabrication process was interrupted", e);
            }

            return "Toy " + toy.getName() + " fabricated in " + totalFabricationTime + " seconds";
        });
    }
}
