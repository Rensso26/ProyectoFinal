package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ManufacturingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fabric")
public class ManufacturingController {

    @Autowired
    private ManufacturingService manufacturingService;

    @PostMapping("/fabricate/{toyId}/{copies}")
    public Mono<Void> fabricateToy(@PathVariable int toyId, @PathVariable int copies) {
        return Mono.fromFuture(manufacturingService.fabricateToys(toyId, copies));
    }
}
