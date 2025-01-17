package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ManufacturingService;
import ec.edu.uce.interfaz.state.Message;
import ec.edu.uce.interfaz.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fabric")
public class ManufacturingController {

    @Autowired
    private ManufacturingService manufacturingService;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/fabricate/{toyId}/{copies}")
    public Mono<Void> fabricateToy(@PathVariable int toyId, @PathVariable int copies) {
        return Mono.fromFuture(manufacturingService.fabricateToys(toyId, copies));
    }

    @GetMapping("/message")
    public Mono<Message> getMessage() {
        return Mono.fromSupplier(() -> messageRepository.findById(1L).orElse(new Message()));
    }
}
