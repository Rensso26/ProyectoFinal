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

    @PostMapping("/fabricate/{toyId}")
    public Mono<String> fabricateToy(@PathVariable int toyId) {
        return manufacturingService.fabricateToy(toyId);
    }
}
