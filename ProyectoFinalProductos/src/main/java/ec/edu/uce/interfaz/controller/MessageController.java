package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.state.Message;
import ec.edu.uce.interfaz.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message")
    public Mono<Message> getMessage() {
        return Mono.fromSupplier(() -> messageRepository.findById(1L).orElse(new Message()));
    }
}
